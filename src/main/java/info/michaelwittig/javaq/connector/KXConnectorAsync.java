package info.michaelwittig.javaq.connector;

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
	 * @return KXConnectorListener
	 */
	KXListener getConnectorListener();
	
}
