package de.cinovo.q.query;

import java.util.Collection;

import de.cinovo.q.Builder;
import de.cinovo.q.Q;
import de.cinovo.q.query.Select.SelectBuilder;
import de.cinovo.q.query.column.Column;

/**
 * Table.
 *
 * @author mwittig
 *
 */
public interface Table extends Q {

	/**
	 * @return Name
	 */
	String getName();

	/**
	 * @return Columns
	 */
	Collection<Column<?>> getColumns();

	/**
	 * @return SelectBuilder
	 */
	SelectBuilder select();

	/**
	 * Table builder.
	 *
	 * @author mwittig
	 *
	 */
	public interface TableBuilder extends Builder<Table> {

		/**
		 * @param column Column
		 * @return TableBuilder
		 */
		TableBuilder column(final Column<?> column);

	}
}
