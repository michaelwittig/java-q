package info.michaelwittig.javaq.connector;

/**
 * TODO rename KX to Q or something
 * 
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
