// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

/**
 * KXConnector.
 * 
 * Error / Exception handling: Errors are failures that the system can handle automatically and to correct the failure situation. For
 * example if the connection is lost and reconnectOnError is true, the KXConnector will try to reconnect to the server. Exceptions are
 * failures that the system can not handle by itself. For example an malformed q command.
 * 
 * @author mwittig
 * 
 */
public abstract interface KXConnector {
	
	/**
	 * Connect.
	 * 
	 * @throws KXException If the connection can not be established
	 * @throws KXError If the KXConnector is already connected
	 */
	void connect() throws KXException, KXError;
	
	/**
	 * Disconnect.
	 * 
	 * @throws KXError If the connection was disconnected already
	 */
	void disconnect() throws KXError;
	
	/**
	 * @return Host
	 */
	String getHost();
	
	/**
	 * @return Port
	 */
	int getPort();
	
	/**
	 * @return Reconnect on error?
	 */
	boolean reconnectOnError();
	
	/**
	 * @return Connected?
	 */
	boolean isConnected();
	
}
