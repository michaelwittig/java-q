package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.Q;
import info.michaelwittig.javaq.query.type.Type;

/**
 * Column.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface Column<T extends Type<?>> extends Q {
	
	/**
	 * @return Name
	 */
	String getName();
	
	/**
	 * @return Type
	 */
	T getType();
	
}
