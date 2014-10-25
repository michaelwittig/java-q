package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.query.type.Type;

/**
 * Aggregate column.
 * 
 * @author mwittig
 * 
 * @param <T> Type
 */
public interface AggregateColumn<T extends Type<?>> extends Column<T>, Virtualling<T> {
	
	/**
	 * @return Aggregation
	 */
	Aggregation getAggregation();
	
}
