package info.michaelwittig.javaq.connector;

import info.michaelwittig.javaq.query.Result;

/**
 * QConnector listener.
 * 
 * @author mwittig
 * 
 */
public interface QConnectorListener {
	
	/**
	 * QConnectorError occurred. Is handled by the QConnector. Just an information.
	 * 
	 * @param e Error
	 */
	void error(QConnectorError e);
	
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
	void resultReceived(final String handle, Result result);
	
}
