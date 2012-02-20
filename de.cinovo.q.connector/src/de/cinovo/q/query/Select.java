package de.cinovo.q.query;

import java.util.List;

import de.cinovo.q.Builder;
import de.cinovo.q.Q;
import de.cinovo.q.query.Select.Sort.Direction;
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
	 * @return Number of rows you wish to return or null (if negative, than the table is reversed!)
	 */
	Integer getNumberOfRows();

	/**
	 * @return Row number you wish to start with
	 */
	Integer getRowNumber();

	/**
	 * @return SelectSort or null
	 */
	Sort getSortColmn();

	/**
	 * Select sort.
	 *
	 * @author mwittig
	 *
	 */
	public interface Sort extends Q {

		/**
		 * Directions.
		 *
		 * @author mwittig
		 *
		 */
		public enum Direction implements Q {
			/** Descending. */
			descending(">"),

			/** Ascending. */
			ascending("<");

			/** Q. */
			private final String q;

			/**
			 * @param aQ Q
			 */
			private Direction(final String aQ) {
				this.q = aQ;
			}

			@Override
			public String toQ() {
				return this.q;
			}
		}

		/**
		 * @return Column
		 */
		Column<?> getColumn();

		/**
		 * @return Direction
		 */
		Direction getDirection();

	}

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

		/**
		 * @param numberOfRows Number of rows
		 * @return SelectBuilder
		 */
		SelectBuilder limit(int numberOfRows);

		/**
		 * @param rowNumber Row number
		 * @return SelectBuilder
		 */
		SelectBuilder start(int rowNumber);

		/**
		 * @param direction Direction
		 * @param column Column
		 * @return SelectBuilder
		 */
		SelectBuilder order(Direction direction, Column<?> column);

	}

}
