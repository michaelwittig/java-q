package de.cinovo.q.query;

import java.util.List;

import de.cinovo.q.Builder;
import de.cinovo.q.Q;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.group.Group;

/**
 * Select.
 *
 * @author mwittig
 *
 */
public interface Select extends Q {

	/**
	 * @return Columns
	 */
	List<Column<?>> getColumns();

	/**
	 * @return Groups
	 */
	List<Group> getGroups();

	/**
	 * @return Table
	 */
	Table getTable();

	/**
	 * @return Filters
	 */
	List<Filter> getFilters();

	/**
	 * Select builder.
	 *
	 * @author mwittig
	 *
	 */
	public interface SelectBuilder extends Builder<Select> {

		/**
		 * @param column Column
		 * @return SelectBuilder
		 */
		SelectBuilder column(Column<?> column);

		/**
		 * @param group Group
		 * @return SelectBuilder
		 */
		SelectBuilder group(Group group);

		/**
		 * @param filter Filter
		 * @return SelectBuilder
		 */
		SelectBuilder filter(Filter filter);

	}

}
