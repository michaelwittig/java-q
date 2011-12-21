// -------------------------------------------------------------------------------
// Copyright (c) 2011 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kx.c;

import de.cinovo.q.connector.KXTable;
import de.cinovo.q.connector.KXTableRow;

/**
 * KXTable.
 *
 * @author mwittig
 *
 */
public final class KXTableImpl implements KXTable {

	/** Table. */
	private final String table;

	/** Columns. */
	private final String[] cols;

	/** Column name to index. */
	private final Map<String, Integer> col2index = new HashMap<String, Integer>();

	/** Data. */
	private final Object[] data;

	/**
	 * @param aTable table
	 * @param aColumns Column names
	 * @param aData Data
	 */
	protected KXTableImpl(final String aTable, final String[] aColumns, final Object[] aData) {
		super();
		this.table = aTable;
		this.cols = aColumns;
		for (int i = 0; i < aColumns.length; i++) {
			this.col2index.put(aColumns[i], i);
		}
		this.data = aData;
	}

	/**
	 * @param name Column name
	 * @return Column index
	 */
	private int colName2Index(final String name) {
		final Integer i = this.col2index.get(name);
		if (i == null) {
			throw new IndexOutOfBoundsException("Column " + name + " not found");
		}
		return i;
	}

	@Override
	public Iterator<KXTableRow> iterator() {
		return new KXTableIterator(this);
	}

	@Override
	public KXTableRow getRow(final int row) {
		return new KXTableRowImpl(this, row);
	}

	@Override
	public Object getAt(final int col, final int row) {
		if (this.data[col] instanceof int[]) {
			final int[] colData = (int[]) this.data[col];
			return colData[row];
		} else if (this.data[col] instanceof long[]) {
			final long[] colData = (long[]) this.data[col];
			return colData[row];
		} else if (this.data[col] instanceof float[]) {
			final float[] colData = (float[]) this.data[col];
			return colData[row];
		} else if (this.data[col] instanceof double[]) {
			final double[] colData = (double[]) this.data[col];
			return colData[row];
		} else {
			final Object[] colData = (Object[]) this.data[col];
			return colData[row];
		}
		
	}

	@Override
	public Object getAt(final String col, final int row) {
		final Object[] colData = (Object[]) this.data[this.colName2Index(col)];
		return colData[row];
	}

	@Override
	public String getStringAt(final String col, final int row) {
		final String[] colData = (String[]) this.data[this.colName2Index(col)];
		return colData[row];
	}

	@Override
	public float getFloatAt(final String col, final int row) {
		final float[] colData = (float[]) this.data[this.colName2Index(col)];
		return colData[row];
	}

	@Override
	public Timestamp getTimestampAt(final String col, final int row) {
		final Timestamp[] colData = (Timestamp[]) this.data[this.colName2Index(col)];
		return colData[row];
	}

	@Override
	public int getCols() {
		return this.data.length;
	}

	@Override
	public List<String> getColNames() {
		final List<String> cols = new ArrayList<String>(this.getCols());
		for (final String c : this.cols) {
			cols.add(c);
		}
		return cols;
	}

	@Override
	public int getRows() {
		return Array.getLength(this.data[0]);
	}

	@Override
	public String getTable() {
		return this.table;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (final String col : this.getColNames()) {
			sb.append(String.format("%10s ", col));
		}
		sb.append(System.getProperty("line.separator"));
		for (final String col : this.getColNames()) {
			sb.append(String.format("%10s ", "--"));
		}
		sb.append(System.getProperty("line.separator"));
		for (final KXTableRow row : this) {
			for (int i = 0; i < this.getCols(); i++) {
				sb.append(String.format("%10s ", row.get(i).toString()));
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	/**
	 * KXTable iterator.
	 *
	 * @author mwittig
	 *
	 */
	private static final class KXTableIterator implements Iterator<KXTableRow> {

		/** Table. */
		private final KXTable table;

		/** Current index. (-1 := not yet started) */
		private int currentI = -1;

		/**
		 * @param aTable Table
		 */
		private KXTableIterator(final KXTable aTable) {
			this.table = aTable;
		}

		@Override
		public boolean hasNext() {
			if ((this.currentI + 1) < this.table.getRows()) {
				return true;
			}
			return false;
		}

		@Override
		public KXTableRow next() {
			this.currentI++;
			return this.table.getRow(this.currentI);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
