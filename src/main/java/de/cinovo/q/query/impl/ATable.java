// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.cinovo.q.query.Result;
import de.cinovo.q.query.Select.SelectBuilder;
import de.cinovo.q.query.Table;
import de.cinovo.q.query.TableResult;
import de.cinovo.q.query.TableRow;
import de.cinovo.q.query.column.Column;

/**
 * Abstract table.
 * 
 * @author mwittig
 * 
 */
public abstract class ATable implements Table {

	/** Name. */
	private final String name;

	/** Columns. */
	private final Map<String, Column<?>> columns = new HashMap<String, Column<?>>();

	/**
	 * @param aName
	 *            Name
	 */
	public ATable(final String aName) {
		this.name = aName;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	/**
	 * @param column
	 *            Column to add
	 */
	protected final void addColumn(final Column<?> column) {
		this.columns.put(column.getName(), column);
	}

	@Override
	public final Collection<Column<?>> getColumns() {
		return this.columns.values();
	}

	@Override
	public final String toQ() {
		return this.name;
	}

	@Override
	public final SelectBuilder select() {
		return new SelectImpl.SelectBuilderImpl(this);
	}

	@Override
	public final Iterable<TableRow> read(final Result result) {
		if (result instanceof TableResult) {
			return new Iterable<TableRow>() {
				@Override
				public Iterator<TableRow> iterator() {
					return new TableIterator((TableResult) result);
				}
			};
		}
		throw new IllegalArgumentException("Your result is not a table, its of type: " + result.getClass().getSimpleName());
	}

}
