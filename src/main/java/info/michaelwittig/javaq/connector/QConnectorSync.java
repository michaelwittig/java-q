package info.michaelwittig.javaq.connector;

import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

/**
 * QConnector synchronous.
 * 
 * Thread-safe? depends on implementation
 * 
 * @author mwittig
 * 
 */
public interface QConnectorSync extends QConnector {
	
	/**
	 * Synchronous execute.
	 * 
	 * @param q Q code
	 * @return Result
	 * @throws QConnectorException If something went wrong
	 */
	Result execute(String q) throws QConnectorException;
	
	/**
	 * Synchronous select.
	 * 
	 * @param select Select
	 * @return Result
	 * @throws QConnectorException If something went wrong
	 */
	Result select(Select select) throws QConnectorException;
	
	/**
	 * Synchronous call.
	 * 
	 * @param function Function
	 * @return Result
	 * @throws QConnectorException If something went wrong
	 */
	Result call(Function function) throws QConnectorException;
	
}
