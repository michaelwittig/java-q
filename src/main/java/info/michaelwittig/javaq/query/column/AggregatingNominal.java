package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.query.type.Type;

/**
 * Aggregating.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface AggregatingNominal<T extends Type<?>> {
	
	/**
	 * @return First
	 */
	AggregateColumn<T> first();
	
	/**
	 * @return Last
	 */
	AggregateColumn<T> last();
	
	/**
	 * @return Count
	 */
	AggregateColumn<T> count();
	
}
