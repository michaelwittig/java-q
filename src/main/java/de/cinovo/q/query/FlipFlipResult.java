// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import kx.c;
import kx.c.Flip;
import de.cinovo.q.query.column.Column;

/**
 * Flip flip result. (Keyed tables)
 * 
 * @author mwittig
 * 
 */
public final class FlipFlipResult extends ATableResult {
	
	/** Key flip. */
	private final c.Flip key;
	
	/** Data flip. */
	private final c.Flip data;
	
	/** Key column name 2 index. */
	private final Map<String, Integer> keyColName2Index;
	
	/** Data column name 2 index. */
	private final Map<String, Integer> dataColName2Index;
	
	/** Column names. */
	private final String[] cols;
	
	
	/**
	 * @param aName Name
	 * @param aKey Key flip
	 * @param aData Data flip
	 */
	public FlipFlipResult(final String aName, final Flip aKey, final Flip aData) {
		super(aName);
		this.key = aKey;
		this.data = aData;
		final HashMap<String, Integer> keyTmp = new HashMap<String, Integer>();
		for (int i = 0; i < this.key.x.length; i++) {
			keyTmp.put(this.key.x[i], i);
		}
		this.keyColName2Index = Collections.unmodifiableMap(keyTmp);
		final HashMap<String, Integer> dataTmp = new HashMap<String, Integer>();
		for (int i = 0; i < this.data.x.length; i++) {
			dataTmp.put(this.data.x[i], i);
		}
		this.dataColName2Index = Collections.unmodifiableMap(dataTmp);
		
		this.cols = new String[this.getCols()];
		for (int i = 0; i < this.key.x.length; i++) {
			this.cols[i] = this.key.x[i];
		}
		for (int i = 0; i < this.data.x.length; i++) {
			this.cols[i + this.key.x.length] = this.data.x[i];
		}
	}
	
	@Override
	public int getRows() {
		return Array.getLength(this.key.y[0]);
	}
	
	@Override
	public int getCols() {
		return this.key.x.length + this.data.x.length;
	}
	
	@Override
	public Object getAt(final Column<?> col, final int row) {
		return this.getAt(col.getName(), row);
	}
	
	@Override
	Object getAt(final String col, final int row) {
		if (this.keyColName2Index.containsKey(col)) {
			return this.getAt(this.key, this.keyColName2Index.get(col), row);
		} else if (this.dataColName2Index.containsKey(col)) {
			return this.getAt(this.data, this.dataColName2Index.get(col), row);
		}
		throw new IllegalArgumentException("Column not found in table");
	}
	
	@Override
	String[] getColNames() {
		return this.cols;
	}
	
	/**
	 * @param flip Flip
	 * @param col Column index
	 * @param row Row index
	 * @return Object or null
	 */
	private Object getAt(final Flip flip, final int col, final int row) {
		return c.at(flip.y[col], row);
	}
}
