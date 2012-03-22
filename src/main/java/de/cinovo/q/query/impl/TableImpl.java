// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.impl;

import de.cinovo.q.query.Table;
import de.cinovo.q.query.column.Column;

/**
 * Table implementation.
 * 
 * @author mwittig
 * 
 */
public final class TableImpl extends ATable {

	/**
	 * @param aName
	 *            Name
	 */
	private TableImpl(final String aName) {
		super(aName);
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
		 * @param aName
		 *            Name
		 */
		public TableBuilderImpl(final String aName) {
			this.table = new TableImpl(aName);
		}

		@Override
		public TableBuilderImpl column(final Column<?> column) {
			this.table.addColumn(column);
			return this;
		}

		@Override
		public Table build() {
			return this.table;
		}
	}

}
