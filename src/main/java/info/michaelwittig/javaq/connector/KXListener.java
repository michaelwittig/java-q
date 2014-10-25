package info.michaelwittig.javaq.connector;

import info.michaelwittig.javaq.query.Result;

/**
 * KX listener.
 * 
 * @author mwittig
 * 
 */
public interface KXListener {
	
	/**
	 * KXError occurred. Is handled by the KXConnector. Just an information.
	 * 
	 * @param e Error
	 */
	void error(KXError e);
	
	/**
	 * KXException occurred. Must be handled by yourself!
	 * 
	 * @param e Exception
	 */
	void exception(KXException e);
	
	/**
	 * Result received.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param result Result
	 */
	void resultReceived(final String handle, Result result);
	
}
