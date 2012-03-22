// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.demo.tabledefinition;

import de.cinovo.q.query.column.impl.FloatColumn;
import de.cinovo.q.query.column.impl.IntegerColumn;
import de.cinovo.q.query.column.impl.SymbolColumn;
import de.cinovo.q.query.column.impl.TimeColumn;
import de.cinovo.q.query.impl.ATable;

/**
 * MyTable definition.
 * 
 * @author mwittig
 * 
 */
public final class MyTable extends ATable {

	/** Instance. */
	private static final MyTable INSTANCE = new MyTable();

	/**
	 * @return Instance
	 */
	public static MyTable get() {
		return INSTANCE;
	}

	/** Time. */
	private final TimeColumn time = new TimeColumn("time");

	/** Sym. */
	private final SymbolColumn sym = new SymbolColumn("sym");

	/** Price. */
	private final FloatColumn price = new FloatColumn("price");

	/** Size. */
	private final IntegerColumn size = new IntegerColumn("size");

	/** */
	private MyTable() {
		super("mytable");
		this.addColumn(this.time);
		this.addColumn(this.sym);
		this.addColumn(this.price);
		this.addColumn(this.size);
	}

	/**
	 * @return Time column
	 */
	public TimeColumn time() {
		return this.time;
	}

	/**
	 * @return Symbol column
	 */
	public SymbolColumn sym() {
		return this.sym;
	}

	/**
	 * @return Price column
	 */
	public FloatColumn price() {
		return this.price;
	}

	/**
	 * @return Size column
	 */
	public IntegerColumn size() {
		return this.size;
	}

}
