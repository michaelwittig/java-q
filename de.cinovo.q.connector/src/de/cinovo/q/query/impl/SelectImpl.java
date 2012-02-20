package de.cinovo.q.query.impl;

import java.util.ArrayList;
import java.util.List;

import de.cinovo.q.query.Select;
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

	/**
	 * @param aTable Table
	 */
	private SelectImpl(final Table aTable) {
		super();
		this.table = aTable;
	}

	@Override
	public String toQ() {
		final StringBuilder sb = new StringBuilder("select ");
		for (final Column<?> c : this.columns) {
			sb.append(c.toQ());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" from ");
		sb.append(this.table.toQ());
		sb.append(" where ");
		for (final Filter f : this.filters) {
			sb.append(f.toQ());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
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
		public Select build() {
			return this.select;
		}

	}

}
