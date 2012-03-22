// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
@Deprecated
public final class KXTableImpl implements KXTable {

	/** Table. */
	private final String table;

	/** Columns. */
	private final List<String> cols;

	/** Column name to index. */
	private final Map<String, Integer> col2index = new HashMap<String, Integer>();

	/** Data. */
	private final Object[] data;

	/**
	 * @param aTable
	 *            table
	 * @param aColumns
	 *            Column names
	 * @param aData
	 *            Data
	 */
	protected KXTableImpl(final String aTable, final String[] aColumns, final Object[] aData) {
		super();
		this.table = aTable;
		this.cols = Arrays.asList(aColumns);
		for (int i = 0; i < aColumns.length; i++) {
			this.col2index.put(aColumns[i], i);
		}
		this.data = aData;
	}

	/**
	 * @param aTable
	 *            table
	 * @param flip
	 *            Flip
	 */
	protected KXTableImpl(final String aTable, final c.Flip flip) {
		this(aTable, flip.x, flip.y);
	}

	/**
	 * @param flip
	 *            Flip
	 */
	protected KXTableImpl(final c.Flip flip) {
		this(null, flip.x, flip.y);
	}

	/**
	 * @param keyFlip
	 *            Key flip
	 * @param dataFlip
	 *            Data dlip
	 */
	protected KXTableImpl(final c.Flip keyFlip, final c.Flip dataFlip) {
		this.table = null;
		this.cols = new ArrayList<String>();
		this.cols.addAll(Arrays.asList(keyFlip.x));
		this.cols.addAll(Arrays.asList(dataFlip.x));
		for (int i = 0; i < this.cols.size(); i++) {
			this.col2index.put(this.cols.get(i), i);
		}
		this.data = new Object[this.cols.size()];
		for (int i = 0; i < keyFlip.y.length; i++) {
			this.data[i] = keyFlip.y[i];
		}
		for (int i = 0; i < dataFlip.y.length; i++) {
			this.data[keyFlip.y.length + i] = dataFlip.y[i];
		}
	}

	/**
	 * @param name
	 *            Column name
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
		return c.at(this.data[col], row);
	}

	@Override
	public Object getAt(final String col, final int row) {
		return this.getAt(this.colName2Index(col), row);
	}

	@Override
	public String getStringAt(final String col, final int row) {
		return (String) this.getAt(col, row);
	}

	@Override
	public float getFloatAt(final String col, final int row) {
		return (Float) this.getAt(col, row);
	}

	@Override
	public Timestamp getTimestampAt(final String col, final int row) {
		return (Timestamp) this.getAt(col, row);
	}

	@Override
	public int getCols() {
		return this.data.length;
	}

	@Override
	public List<String> getColNames() {
		return this.cols;
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
	public List<KXTableRow> toList() {
		final List<KXTableRow> rows = new ArrayList<KXTableRow>(this.getRows());
		for (final KXTableRow row : this) {
			rows.add(row);
		}
		return rows;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (final String col : this.getColNames()) {
			sb.append(String.format("%10s ", col));
		}
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < this.getCols(); i++) {
			sb.append(String.format("%10s ", "--"));
		}
		sb.append(System.getProperty("line.separator"));
		for (final KXTableRow row : this) {
			for (int i = 0; i < this.getCols(); i++) {
				final Object value = row.getBy(i);
				if (value == null) {
					sb.append(String.format("%10s ", ""));
				} else {
					sb.append(String.format("%10s ", value.toString()));
				}
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
		 * @param aTable
		 *            Table
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
