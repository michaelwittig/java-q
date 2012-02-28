// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

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

}
