// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.KXTable;
import de.cinovo.q.query.Select;

/**
 * KX Connector implementation.
 *
 * @author mwittig
 *
 */
final class KXConnectorSyncImpl extends KXConnectorImpl implements KXConnectorSync {

	/** Connection. */
	private final AtomicReference<c> c = new AtomicReference<c>();

	/**
	 * @param aHost Host
	 * @param aPort Port
	 */
	protected KXConnectorSyncImpl(final String aHost, final int aPort) {
		super(aHost, aPort, false);
	}

	@Override
	public void connect() throws KXException, KXError {
		try {
			if (!this.c.compareAndSet(null, new c(this.getHost(), this.getPort()))) {
				throw new KXError("Already connected");
			}
		} catch (final KException e) {
			throw new KXException("KException", e);
		} catch (final IOException e) {
			if (this.reconnectOnError()) {
				throw new KXError("Could not connect to " + this.getHost() + ":" + this.getPort());
			} else {
				throw new KXException("Could not connect to " + this.getHost() + ":" + this.getPort(), e);
			}
		}
	}

	@Override
	public void disconnect() throws KXError {
		final c old = this.c.get();
		if (old == null) {
			throw new KXError("Not connected");
		}
		if (!this.c.compareAndSet(old, null)) {
			throw new KXError("Already disconnected");
		}
		try {
			old.close();
		} catch (final IOException e) {
			return;
		}
	}

	@Override
	public KXTable query(final String cmd) throws KXException {
		try {
			return new KXSyncCommandString(cmd).execute(this.c.get());
		} catch (final Exception e) {
			throw new KXException("Query failed", e);
		}
	}

	@Override
	public KXTable select(final Select select) throws KXException {
		try {
			return new KXSyncCommandSelect(select).execute(this.c.get());
		} catch (final Exception e) {
			throw new KXException("Query failed", e);
		}
	}

	@Override
	public final boolean isConnected() {
		if (this.c.get() != null) {
			return true;
		}
		return false;
	}

}
