// -------------------------------------------------------------------------------
// Copyright (c) 2011 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import de.cinovo.q.connector.KXConnector;
import de.cinovo.q.connector.KXConnectorListener;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;

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
	public static KXConnector create(final KXConnectorListener listener, final String host, final int port, final boolean reconnectOnError,
			final boolean connect) throws KXException, KXError {
		final KXConnector c = KXConnectorFactory.create(listener, host, port, reconnectOnError);
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
	public static KXConnector create(final KXConnectorListener listener, final String host, final int port, final boolean reconnectOnError) {
		final KXConnector c = new KXConnectorImpl(listener, host, port, reconnectOnError);
		return c;
	}

	/** */
	private KXConnectorFactory() {
		super();
	}

}
