package info.michaelwittig.javaq.connector;

/**
 * QConnector asynchronous.
 * 
 * Thread-safe? yes
 * 
 * @author mwittig
 * 
 */
public interface QConnectorAsync extends QConnector {
	
	/**
	 * Subscribe and receive data via global QConnectorListener.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param tables Tables to subscribe
	 * @param symbols Symbols to subscribe
	 * @throws QConnectorException If the communication fails or the subscription is corrupt
	 */
	void subscribe(String handle, String[] tables, String[] symbols) throws QConnectorException;
	
	/**
	 * Subscribe and receive data via local QConnectorDataListener.
	 * 
	 * @param listener Listener
	 * @param tables Table to subscribe
	 * @param symbols Symbols to subscribe
	 * @throws QConnectorException If the communication fails or the subscription is corrupt
	 */
	void subscribe(QConnectorDataListener listener, String[] tables, String[] symbols) throws QConnectorException;
	
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
	void unsubscribe(QConnectorDataListener listener);
	
	/**
	 * @return QConnectorListener
	 */
	QConnectorListener getConnectorListener();
	
}
