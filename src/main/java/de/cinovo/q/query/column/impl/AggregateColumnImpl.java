// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.AggregateColumn;
import de.cinovo.q.query.column.Aggregation;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.column.VirtualColumn;
import de.cinovo.q.query.type.Type;

/**
 * Aggregate column implementation.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public final class AggregateColumnImpl<T extends Type<?>> implements AggregateColumn<T> {

	/** Aggregation. */
	private final Aggregation aggregation;

	/** Type. */
	private final Column<T> column;

	/**
	 * @param anAggregation Aggregation
	 * @param aColumn Column
	 */
	protected AggregateColumnImpl(final Aggregation anAggregation, final Column<T> aColumn) {
		this.aggregation = anAggregation;
		this.column = aColumn;
	}

	@Override
	public String getName() {
		return this.column.getName();
	}

	@Override
	public T getType() {
		return this.column.getType();
	}

	@Override
	public String toQ() {
		return this.aggregation.toQ() + " " + this.column.toQ();
	}

	@Override
	public Aggregation getAggregation() {
		return this.aggregation;
	}

	@Override
	public VirtualColumn<T> virtual(final String virtual) {
		return new VirtualColumnImpl<T>(virtual, this);
	}

}
