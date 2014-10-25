package info.michaelwittig.javaq.query.filter;

import info.michaelwittig.javaq.Q;
import info.michaelwittig.javaq.query.column.Column;

/**
 * Filter.
 * 
 * @author mwittig
 * 
 */
public interface Filter extends Q {
	
	/**
	 * @return Column
	 */
	Column<?> getColumn();
	
}
