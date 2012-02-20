package de.cinovo.q.query.impl;

import java.util.ArrayList;
import java.util.List;

import de.cinovo.q.query.Select;
import de.cinovo.q.query.Select.Sort.Direction;
import de.cinovo.q.query.Table;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.group.Group;

/**
 * Select implementation.
 *
 * @author mwittig
 *
 */
public final class SelectImpl implements Select {

	/** Table. */
	private final Table table;

	/** Columns. */
	private final List<Column<?>> columns = new ArrayList<Column<?>>();

	/** Groups. */
	private final List<Group> groups = new ArrayList<Group>();

	/** Filters. */
	private final List<Filter> filters = new ArrayList<Filter>();

	/** Limit. */
	private Integer numberOfRows;

	/** Start. */
	private Integer rowNumber;

	/** Order. */
	private Sort sort;

	/**
	 * @param aTable Table
	 */
	private SelectImpl(final Table aTable) {
		super();
		this.table = aTable;
	}

	/**
	 * @param sb StringBuilder
	 */
	private void sortToQ(final StringBuilder sb) {
		if (this.numberOfRows != null && this.sort != null) {
			sb.append("[");
			sb.append(this.numberOfRows);
			sb.append(";");
			sb.append(this.sort.toQ());
			sb.append("]");
		} else if (this.numberOfRows != null && this.rowNumber != null) {
			sb.append("[");
			sb.append(this.rowNumber);
			sb.append(" ");
			sb.append(this.numberOfRows);
			sb.append("]");
		} else if (this.numberOfRows != null) {
			sb.append("[");
			sb.append(this.numberOfRows);
			sb.append("]");
		} else if (this.sort != null) {
			sb.append("[");
			sb.append(this.sort.toQ());
			sb.append("]");
		}
	}

	/**
	 * @param sb StringBuilder
	 */
	private void filterToQ(final StringBuilder sb) {
		if (this.filters.size() > 0) {
			sb.append(" where ");
			for (final Filter f : this.filters) {
				sb.append(f.toQ());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * @param sb StringBuilder
	 */
	private void groupToQ(final StringBuilder sb) {
		if (this.groups.size() > 0) {
			sb.append(" by ");
			for (final Group g : this.groups) {
				sb.append(g.toQ());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	@Override
	public String toQ() {
		final StringBuilder sb = new StringBuilder("select");
		this.sortToQ(sb);
		sb.append(" ");
		for (final Column<?> c : this.columns) {
			sb.append(c.toQ());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		this.groupToQ(sb);
		sb.append(" from ");
		sb.append(this.table.toQ());
		this.filterToQ(sb);
		return sb.toString();
	}

	@Override
	public List<Column<?>> getColumns() {
		return this.columns;
	}

	@Override
	public List<Group> getGroups() {
		return this.groups;
	}

	@Override
	public Table getTable() {
		return this.table;
	}

	@Override
	public List<Filter> getFilters() {
		return this.filters;
	}

	@Override
	public Integer getNumberOfRows() {
		return this.numberOfRows;
	}

	@Override
	public Integer getRowNumber() {
		return this.rowNumber;
	}

	@Override
	public Sort getSortColmn() {
		return this.sort;
	}


	/**
	 * Select builder implementation.
	 *
	 * @author mwittig
	 *
	 */
	public static final class SelectBuilderImpl implements SelectBuilder {

		/** Select. */
		private final SelectImpl select;

		/**
		 * @param table Table
		 */
		public SelectBuilderImpl(final Table table) {
			this.select = new SelectImpl(table);
		}

		@Override
		public SelectBuilder column(final Column<?> column) {
			this.select.columns.add(column);
			return this;
		}

		@Override
		public SelectBuilder group(final Group group) {
			this.select.groups.add(group);
			return this;
		}

		@Override
		public SelectBuilder filter(final Filter filter) {
			this.select.filters.add(filter);
			return this;
		}

		@Override
		public SelectBuilder limit(final int numberOfRows) {
			this.select.numberOfRows = numberOfRows;
			return this;
		}

		@Override
		public SelectBuilder start(final int rowNumber) {
			this.select.rowNumber = rowNumber;
			return this;
		}

		@Override
		public SelectBuilder order(final Direction direction, final Column<?> column) {
			this.select.sort = new Sort() {

				@Override
				public String toQ() {
					return direction.toQ() + column.toQ();
				}

				@Override
				public Column<?> getColumn() {
					return column;
				}

				@Override
				public Direction getDirection() {
					return direction;
				}
			};
			return this;
		}

		@Override
		public Select build() {
			return this.select;
		}

	}

}
