// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

/**
 * KXConnector asynchronous.
 *
 * Thread-safe? yes
 *
 * @author mwittig
 *
 */
public interface KXConnectorAsync extends KXConnector {

	/**
	 * Subscribe and receive data via global KXConnectorListener.
	 *
	 * @param handle Unique handle to identify received data
	 * @param tables Tables to subscribe
	 * @param symbols Symbols to subscribe
	 * @throws KXException If the communication fails or the subscription is corrupt
	 */
	void subscribe(String handle, String[] tables, String[] symbols) throws KXException;

	/**
	 * Subscribe and receive data via local KXDataListener.
	 *
	 * @param listener Listener
	 * @param tables Table to subscribe
	 * @param symbols Symbols to subscribe
	 * @throws KXException If the communication fails or the subscription is corrupt
	 */
	void subscribe(KXDataListener listener, String[] tables, String[] symbols) throws KXException;

	/**
	 * Unsubscribe.
	 *
	 * @param handle Unique handle to identify received data
	 */
	void unsubscribe(String handle);

	/**
	 * Unsubscribe.
	 *
	 * @param listener Listener
	 */
	void unsubscribe(KXDataListener listener);

	/**
	 * Asynchronous query. Data is received via global KXConnectorListener.
	 *
	 * @param handle Unique handle to identify received data
	 * @param cmd Command
	 * @throws KXException If something went wrong
	 */
	void query(String handle, String cmd) throws KXException;

	/**
	 * Asynchronous query. Data is received via local KXDataListener.
	 *
	 * @param listener Listener
	 * @param cmd Command
	 * @throws KXException If something went wrong
	 */
	void query(KXDataListener listener, String cmd) throws KXException;

	/**
	 * @return KXConnectorListener
	 */
	KXListener getConnectorListener();

}
