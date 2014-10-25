package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.query.type.Type;

/**
 * Virtual column.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface Virtualling<T extends Type<?>> {
	
	/**
	 * @param virtual Virtual
	 * @return Virtual column
	 */
	VirtualColumn<T> virtual(String virtual);
	
}
