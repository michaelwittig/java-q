// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import de.cinovo.q.connector.KXConnectorAsync;
import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.KXListener;

/**
 * Creates KXConnectors.
 *
 * @author mwittig
 *
 */
public final class KXConnectorFactory {

	/**
	 * @param listener Listener
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @param connect Connect?
	 * @throws KXException If the connection can not be established
	 * @throws KXError If the KXConnector is already connected
	 * @return KXConnector
	 */
	public static KXConnectorAsync create(final KXListener listener, final String host, final int port, final boolean reconnectOnError,
			final boolean connect) throws KXException, KXError {
		final KXConnectorAsync c = KXConnectorFactory.create(listener, host, port, reconnectOnError);
		if (connect) {
			c.connect();
		}
		return c;
	}

	/**
	 * @param listener Listener
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @return KXConnector
	 */
	public static KXConnectorAsync create(final KXListener listener, final String host, final int port, final boolean reconnectOnError) {
		final KXConnectorAsync c = new KXConnectorAsyncImpl(listener, host, port, reconnectOnError);
		return c;
	}

	/**
	 * @param host Host
	 * @param port Port
	 * @param connect Connect?
	 * @throws KXException If the connection can not be established
	 * @throws KXError If the KXConnector is already connected
	 * @return KXConnector
	 */
	public static KXConnectorSync create(final String host, final int port, final boolean connect) throws KXException, KXError {
		final KXConnectorSync c = KXConnectorFactory.create(host, port);
		if (connect) {
			c.connect();
		}
		return c;
	}

	/**
	 * @param host Host
	 * @param port Port
	 * @return KXConnector
	 */
	public static KXConnectorSync create(final String host, final int port) {
		final KXConnectorSync c = new KXConnectorSyncImpl(host, port);
		return c;
	}

	/** */
	private KXConnectorFactory() {
		super();
	}

}
