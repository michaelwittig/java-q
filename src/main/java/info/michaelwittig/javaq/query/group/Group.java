package info.michaelwittig.javaq.query.group;

import info.michaelwittig.javaq.Q;
import info.michaelwittig.javaq.query.column.Column;

/**
 * Group.
 * 
 * @author mwittig
 * 
 */
public interface Group extends Q {
	
	/**
	 * @return Column
	 */
	Column<?> getColumn();
	
}
