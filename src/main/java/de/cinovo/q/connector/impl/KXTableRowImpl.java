// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.sql.Timestamp;

import de.cinovo.q.connector.KXTable;
import de.cinovo.q.connector.KXTableRow;

/**
 * KXTableRow.
 * 
 * @author mwittig
 * 
 */
@Deprecated
public final class KXTableRowImpl implements KXTableRow {

	/** Data. */
	private final KXTable table;

	/** Row. */
	private final int row;

	/**
	 * @param aTable
	 *            Table
	 * @param aRow
	 *            Row
	 */
	protected KXTableRowImpl(final KXTable aTable, final int aRow) {
		super();
		this.table = aTable;
		this.row = aRow;
	}

	@Override
	public Object getBy(final int col) {
		return this.table.getAt(col, this.row);
	}

	@Override
	public Object get(final String col) {
		return this.table.getAt(col, this.row);
	}

	@Override
	public String getString(final String col) {
		return this.table.getStringAt(col, this.row);
	}

	@Override
	public float getFloat(final String col) {
		return this.table.getFloatAt(col, this.row);
	}

	@Override
	public Timestamp getTimestamp(final String col) {
		return this.table.getTimestampAt(col, this.row);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.table.getCols(); i++) {
			sb.append(String.format("%10s ", this.getBy(i).toString()));
		}
		return sb.toString();
	}

}
