package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.KXConnectorSync;
import info.michaelwittig.javaq.connector.KXError;
import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.connector.impl.cmd.KXSyncCommand;
import info.michaelwittig.javaq.connector.impl.cmd.KXSyncCommandFunction;
import info.michaelwittig.javaq.connector.impl.cmd.KXSyncCommandQ;
import info.michaelwittig.javaq.connector.impl.cmd.KXSyncCommandSelect;
import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

import java.io.IOException;
import java.util.TimeZone;

import kx.c;
import kx.c.KException;

/**
 * KX Connector implementation.
 * 
 * Thread-safe? no
 * 
 * @author mwittig
 * 
 */
final class KXConnectorSyncImpl extends KXConnectorImpl implements KXConnectorSync {
	
	/** Connection. */
	private c c = null;
	
	
	/**
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected KXConnectorSyncImpl(final String aHost, final int aPort, final boolean aReconnectOnError) {
		super(aHost, aPort, aReconnectOnError);
	}
	
	@Override
	public void connect() throws KXException, KXError {
		try {
			if (this.c != null) {
				throw new KXError("Already connected");
			}
			this.c = new c(this.getHost(), this.getPort());
			this.c.tz = TimeZone.getTimeZone("UTC");
		} catch (final KException e) {
			throw new KXException("KException", e);
		} catch (final IOException e) {
			throw new KXException("Could not connect to " + this.getHost() + ":" + this.getPort(), e);
		}
	}
	
	@Override
	public void disconnect() throws KXError {
		if (this.c == null) {
			throw new KXError("Not connected");
		}
		try {
			this.c.close();
		} catch (final IOException e) {
			// supress
		}
		this.c = null;
	}
	
	private Result cmd(final KXSyncCommand cmd) throws KXException {
		return this.cmd(cmd, false);
	}
	
	private Result cmd(final KXSyncCommand cmd, final boolean isReconnectAttemp) throws KXException {
		if (!this.isConnected()) {
			try {
				this.connect();
			} catch (final KXError e) {
				// can not happen
			}
		}
		try {
			return cmd.execute(this.c);
		} catch (final IOException e) {
			if ((isReconnectAttemp == false) && this.reconnectOnError()) {
				try {
					Thread.sleep(KXConnectorImpl.RECONNECT_OFFSET_PER_TRY);
				} catch (final InterruptedException ie) {
					// supress
				}
				try {
					this.disconnect();
					this.connect();
				} catch (final KXError kxe) {
					throw new KXException("Can not reconnect", kxe);
				}
				return this.cmd(cmd, true);
			}
			throw new KXException("Socket failed", e);
		} catch (final KException e) {
			throw new KXException("Q failed", e);
		}
	}
	
	@Override
	public Result execute(final String q) throws KXException {
		return this.cmd(new KXSyncCommandQ(q));
	}
	
	@Override
	public Result select(final Select select) throws KXException {
		return this.cmd(new KXSyncCommandSelect(select));
	}
	
	@Override
	public Result call(final Function function) throws KXException {
		return this.cmd(new KXSyncCommandFunction(function));
	}
	
	@Override
	public boolean isConnected() {
		if (this.c != null) {
			return true;
		}
		return false;
	}
	
}
