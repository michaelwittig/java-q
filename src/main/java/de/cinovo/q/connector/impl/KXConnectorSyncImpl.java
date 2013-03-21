// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.io.IOException;
import java.util.TimeZone;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.impl.cmd.KXSyncCommand;
import de.cinovo.q.connector.impl.cmd.KXSyncCommandFunction;
import de.cinovo.q.connector.impl.cmd.KXSyncCommandQ;
import de.cinovo.q.connector.impl.cmd.KXSyncCommandSelect;
import de.cinovo.q.query.Function;
import de.cinovo.q.query.Result;
import de.cinovo.q.query.Select;

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
			e.printStackTrace();
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
				e.printStackTrace(); // can not happen
			}
		}
		try {
			return cmd.execute(this.c);
		} catch (final IOException e) {
			if ((isReconnectAttemp == false) && this.reconnectOnError()) {
				try {
					Thread.sleep(KXConnectorImpl.RECONNECT_OFFSET_PER_TRY);
				} catch (final InterruptedException ie) {
					ie.printStackTrace();
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
