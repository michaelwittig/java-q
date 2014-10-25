package info.michaelwittig.javaq.connector;

import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

/**
 * KXConnector synchronous.
 * 
 * Thread-safe? depends on implementation
 * 
 * @author mwittig
 * 
 */
public interface KXConnectorSync extends KXConnector {
	
	/**
	 * Synchronous execute.
	 * 
	 * @param q Q code
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result execute(String q) throws KXException;
	
	/**
	 * Synchronous select.
	 * 
	 * @param select Select
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result select(Select select) throws KXException;
	
	/**
	 * Synchronous call.
	 * 
	 * @param function Function
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result call(Function function) throws KXException;
	
}
