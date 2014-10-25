package info.michaelwittig.javaq.connector;

/**
 * QConnector.
 * 
 * Error / Exception handling: Errors are failures that the system can handle automatically and to correct the failure situation. For
 * example if the connection is lost and reconnectOnError is true, the QConnector will try to reconnect to the server. Exceptions are
 * failures that the system can not handle by itself. For example an malformed q command.
 * 
 * @author mwittig
 * 
 */
public abstract interface QConnector {
	
	/**
	 * Connect.
	 * 
	 * @throws QConnectorException If the connection can not be established
	 * @throws QConnectorError If the QConnector is already connected
	 */
	void connect() throws QConnectorException, QConnectorError;
	
	/**
	 * Disconnect.
	 * 
	 * @throws QConnectorError If the connection was disconnected already
	 */
	void disconnect() throws QConnectorError;
	
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
