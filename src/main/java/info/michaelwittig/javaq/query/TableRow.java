package info.michaelwittig.javaq.query;

import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Result.
 * 
 * @author mwittig
 * 
 */
public interface TableRow {
	
	/**
	 * @param column Column
	 * @param <J> Java type
	 * @param <T> Type of column
	 * @return Value for this column in the row
	 */
	<J, T extends Type<J>> Value<J, T> get(Column<T> column);
	
}
