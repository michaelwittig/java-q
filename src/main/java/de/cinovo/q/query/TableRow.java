package de.cinovo.q.query;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.value.Value;

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
