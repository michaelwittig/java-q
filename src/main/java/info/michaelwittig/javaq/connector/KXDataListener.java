package info.michaelwittig.javaq.connector;

import info.michaelwittig.javaq.query.Result;

/**
 * KX data listener.
 * 
 * @author mwittig
 * 
 */
public interface KXDataListener {
	
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
	void resultReceived(Result result);
	
}
