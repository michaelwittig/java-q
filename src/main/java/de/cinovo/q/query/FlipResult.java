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
 * Flip result.
 * 
 * @author mwittig
 * 
 */
public final class FlipResult extends ATableResult {
	
	/** Flip. */
	private final c.Flip flip;
	
	/** Column name 2 index. */
	private final Map<String, Integer> colName2Index;
	
	
	/**
	 * @param aFlip Flip
	 */
	public FlipResult(final String aName, final Flip aFlip) {
        super(aName);
		this.flip = aFlip;
		final HashMap<String, Integer> tmp = new HashMap<String, Integer>();
		for (int i = 0; i < this.flip.x.length; i++) {
			tmp.put(this.flip.x[i], i);
		}
		this.colName2Index = Collections.unmodifiableMap(tmp);
	}
	
	@Override
	public int getRows() {
		return Array.getLength(this.flip.y[0]);
	}
	
	@Override
	public int getCols() {
		return this.flip.x.length;
	}
	
	/**
	 * @param col Column
	 * @param row Row index
	 * @return Object or null
	 */
	@Override
	public Object getAt(final Column<?> col, final int row) {
		return this.getAt(col.getName(), row);
	}
	
	@Override
	Object getAt(final String col, final int row) {
		if (this.colName2Index.containsKey(col)) {
			return this.getAt(this.colName2Index.get(col), row);
		}
		throw new IllegalArgumentException("Column not found in table");
	}
	
	@Override
	String[] getColNames() {
		return this.flip.x;
	}
	
	/**
	 * @param col Column index
	 * @param row Row index
	 * @return Object or null
	 */
	private Object getAt(final int col, final int row) {
		return c.at(this.flip.y[col], row);
	}
	
}
