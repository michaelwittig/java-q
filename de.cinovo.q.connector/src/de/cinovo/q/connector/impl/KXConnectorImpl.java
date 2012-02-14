// -------------------------------------------------------------------------------
// Copyright (c) 2011 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import de.cinovo.q.connector.KXConnector;

/**
 * KX Connector implementation.
 *
 * @author mwittig
 *
 */
abstract class KXConnectorImpl implements KXConnector {

	/** Host. */
	private final String host;

	/** Port. */
	private final int port;

	/** Reconnect on error? */
	private final boolean reconnectOnError;

	/** Connection. */
	private final AtomicReference<c> c = new AtomicReference<c>();

	/**
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected KXConnectorImpl(final String aHost, final int aPort, final boolean aReconnectOnError) {
		super();
		this.host = aHost;
		this.port = aPort;
		this.reconnectOnError = aReconnectOnError;
	}

	@Override
	public final String getHost() {
		return this.host;
	}

	@Override
	public final int getPort() {
		return this.port;
	}

	@Override
	public final boolean reconnectOnError() {
		return this.reconnectOnError;
	}

	@Override
	public final boolean isConnected() {
		if (this.c.get() != null) {
			return true;
		}
		return false;
	}

}
