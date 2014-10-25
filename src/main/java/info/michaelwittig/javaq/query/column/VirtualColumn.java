package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.query.type.Type;

/**
 * Virtual column.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface VirtualColumn<T extends Type<?>> extends Column<T> {
	
	/**
	 * @return Virtual
	 */
	String getVirtual();
	
}
