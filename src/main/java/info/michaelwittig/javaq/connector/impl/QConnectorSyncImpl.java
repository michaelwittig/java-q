package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.QConnectorError;
import info.michaelwittig.javaq.connector.QConnectorSync;
import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommand;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommandFunction;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommandQ;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommandSelect;
import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

import java.io.IOException;
import java.util.TimeZone;

import kx.c;
import kx.c.KException;

/**
 * Q Connector implementation.
 * 
 * Thread-safe? no
 * 
 * @author mwittig
 * 
 */
final class QConnectorSyncImpl extends QConnectorImpl implements QConnectorSync {
	
	/** Connection. */
	private c c = null;
	
	
	/**
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected QConnectorSyncImpl(final String aHost, final int aPort, final boolean aReconnectOnError) {
		super(aHost, aPort, aReconnectOnError);
	}
	
	@Override
	public void connect() throws QConnectorException, QConnectorError {
		try {
			if (this.c != null) {
				throw new QConnectorError("Already connected");
			}
			this.c = new c(this.getHost(), this.getPort());
			this.c.tz = TimeZone.getTimeZone("UTC");
		} catch (final KException e) {
			throw new QConnectorException("KException", e);
		} catch (final IOException e) {
			throw new QConnectorException("Could not connect to " + this.getHost() + ":" + this.getPort(), e);
		}
	}
	
	@Override
	public void disconnect() throws QConnectorError {
		if (this.c == null) {
			throw new QConnectorError("Not connected");
		}
		try {
			this.c.close();
		} catch (final IOException e) {
			// supress
		}
		this.c = null;
	}
	
	private Result cmd(final ConnectorSyncCommand cmd) throws QConnectorException {
		return this.cmd(cmd, false);
	}
	
	private Result cmd(final ConnectorSyncCommand cmd, final boolean isReconnectAttemp) throws QConnectorException {
		if (!this.isConnected()) {
			try {
				this.connect();
			} catch (final QConnectorError e) {
				// can not happen
			}
		}
		try {
			return cmd.execute(this.c);
		} catch (final IOException e) {
			if ((isReconnectAttemp == false) && this.reconnectOnError()) {
				try {
					Thread.sleep(QConnectorImpl.RECONNECT_OFFSET_PER_TRY);
				} catch (final InterruptedException ie) {
					// supress
				}
				try {
					this.disconnect();
					this.connect();
				} catch (final QConnectorError kxe) {
					throw new QConnectorException("Can not reconnect", kxe);
				}
				return this.cmd(cmd, true);
			}
			throw new QConnectorException("Socket failed", e);
		} catch (final KException e) {
			throw new QConnectorException("Q failed", e);
		}
	}
	
	@Override
	public Result execute(final String q) throws QConnectorException {
		return this.cmd(new ConnectorSyncCommandQ(q));
	}
	
	@Override
	public Result select(final Select select) throws QConnectorException {
		return this.cmd(new ConnectorSyncCommandSelect(select));
	}
	
	@Override
	public Result call(final Function function) throws QConnectorException {
		return this.cmd(new ConnectorSyncCommandFunction(function));
	}
	
	@Override
	public boolean isConnected() {
		if (this.c != null) {
			return true;
		}
		return false;
	}
	
}
