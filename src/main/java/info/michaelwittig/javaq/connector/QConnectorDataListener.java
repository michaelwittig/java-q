package info.michaelwittig.javaq.connector;

import info.michaelwittig.javaq.query.Result;

/**
 * QConnector data listener.
 * 
 * @author mwittig
 * 
 */
public interface QConnectorDataListener {
	
	/**
	 * QConnectorException occurred. Must be handled by yourself!
	 * 
	 * @param e Exception
	 */
	void exception(QConnectorException e);
	
	/**
	 * Result received.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param result Result
	 */
	void resultReceived(Result result);
	
}
