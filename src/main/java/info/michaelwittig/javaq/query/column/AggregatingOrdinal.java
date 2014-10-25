package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.query.type.Type;

/**
 * Aggregating.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface AggregatingOrdinal<T extends Type<?>> extends AggregatingNominal<T> {
	
	/**
	 * @return Minimum
	 */
	AggregateColumn<T> min();
	
	/**
	 * @return maximum
	 */
	AggregateColumn<T> max();
	
	/**
	 * @return Average
	 */
	AggregateColumn<T> avg();
	
	/**
	 * @return Sum
	 */
	AggregateColumn<T> sum();
	
}
