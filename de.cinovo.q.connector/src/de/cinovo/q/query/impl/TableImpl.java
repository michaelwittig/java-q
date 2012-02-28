// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.cinovo.q.query.Select.SelectBuilder;
import de.cinovo.q.query.Table;
import de.cinovo.q.query.TableResultWrapper;
import de.cinovo.q.query.column.Column;

/**
 * Table implementation.
 *
 * @author mwittig
 *
 */
public final class TableImpl implements Table {

	/** Name. */
	private final String name;

	/** Columns. */
	private final Map<String, Column<?>> columns = new HashMap<String, Column<?>>();

	/**
	 * @param aName Name
	 */
	private TableImpl(final String aName) {
		this.name = aName;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Collection<Column<?>> getColumns() {
		return this.columns.values();
	}

	@Override
	public String toQ() {
		return this.name;
	}

	@Override
	public SelectBuilder select() {
		return new SelectImpl.SelectBuilderImpl(this);
	}

	@Override
	public TableResultWrapper<Table> wrap(final Object res) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Table builder implementation.
	 *
	 * @author mwittig
	 *
	 */
	public static final class TableBuilderImpl implements TableBuilder {

		/** Table. */
		private final TableImpl table;

		/**
		 * @param aName Name
		 */
		public TableBuilderImpl(final String aName) {
			this.table = new TableImpl(aName);
		}

		@Override
		public TableBuilderImpl column(final Column<?> column) {
			this.table.columns.put(column.getName(), column);
			return this;
		}

		@Override
		public Table build() {
			return this.table;
		}
	}

}
