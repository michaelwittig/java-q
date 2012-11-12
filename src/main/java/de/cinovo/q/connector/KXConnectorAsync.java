// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

import de.cinovo.q.query.Function;
import de.cinovo.q.query.Select;

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
	 * Asynchronous execute. Data is received via global KXConnectorListener.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param q Q code
	 * @throws KXException If something went wrong
	 */
	void execute(String handle, String q) throws KXException;
	
	/**
	 * Asynchronous execute. Data is received via local KXDataListener.
	 * 
	 * @param listener Listener
	 * @param q Q code
	 * @throws KXException If something went wrong
	 */
	void execute(KXDataListener listener, String q) throws KXException;
	
	/**
	 * Asynchronous select.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param select Select
	 * @throws KXException If something went wrong
	 */
	void select(String handle, Select select) throws KXException;
	
	/**
	 * Asynchronous select.
	 * 
	 * @param listener Listener
	 * @param select Select
	 * @throws KXException If something went wrong
	 */
	void select(KXDataListener listener, Select select) throws KXException;
	
	/**
	 * Asynchronous call.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param function Function
	 * @throws KXException If something went wrong
	 */
	void call(String handle, Function function) throws KXException;
	
	/**
	 * Asynchronous call.
	 * 
	 * @param listener Listener
	 * @param function Function
	 * @throws KXException If something went wrong
	 */
	void call(KXDataListener listener, Function function) throws KXException;
	
	/**
	 * @return KXConnectorListener
	 */
	KXListener getConnectorListener();
	
}
