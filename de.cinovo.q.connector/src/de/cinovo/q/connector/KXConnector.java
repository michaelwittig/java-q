package de.cinovo.q.connector;

/**
 * KXConnector.
 *
 * Thread-safe? no
 *
 * Error / Exception handling:
 * Errors are failures that the system can handle automatically and to correct the failure situation.
 * For example if the connection is lost and reconnectOnError is true, the KXConnector will try to reconnect to the server.
 *
 * Exceptions are failures that the system can not handle by itself. For example an malformed q command.
 */
public interface KXConnector {

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
	 * @throws KXError If the connection was disconneccted already
	 */
	void disconnect() throws KXError;

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
	 * Synchronous call.
	 *
	 * @param cmd Command
	 * @return KX table
	 */
	KXTable sync(String cmd);

	/**
	 * Asynchronous call. Data is received via global KXConnectorListener.
	 *
	 * @param handle Unique handle to identify received data
	 * @param cmd Command
	 */
	void async(String handle, String cmd);

	/**
	 * Asynchronous call. Data is received via local KXDataListener.
	 *
	 * @param listener Listener
	 * @param cmd Command
	 */
	void async(KXDataListener listener, String cmd);

	/**
	 * @return KXConnectorListener
	 */
	KXConnectorListener getConnectorListener();

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
