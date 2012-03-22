// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.AggregateColumn;
import de.cinovo.q.query.column.AggregatingOrdinal;
import de.cinovo.q.query.column.Aggregation;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.filter.ComparisonFiltering;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.filter.impl.FilterComparator;
import de.cinovo.q.query.group.Group;
import de.cinovo.q.query.group.XbarGrouping;
import de.cinovo.q.query.group.impl.XbarGroupImpl;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.LongValue;

/**
 * Abstract simple ordinal column.
 * 
 * @author mwittig
 * 
 * @param <J>
 *            Java type
 * @param <T>
 *            Type
 */
public abstract class ASimpleOrdinalColumn<J, T extends OrdinalType<J>> extends ASimpleNominalColumn<J, T> implements ComparisonFiltering<J, T>, XbarGrouping<T>,
		AggregatingOrdinal<T> {

	/**
	 * @param aName
	 *            Name
	 * @param aType
	 *            Type
	 */
	public ASimpleOrdinalColumn(final String aName, final T aType) {
		super(aName, aType);
	}

	@Override
	public final AggregateColumn<T> min() {
		return this.createAggregation(Aggregation.min);
	}

	@Override
	public final AggregateColumn<T> max() {
		return this.createAggregation(Aggregation.max);
	}

	@Override
	public final AggregateColumn<T> avg() {
		return this.createAggregation(Aggregation.avg);
	}

	@Override
	public final AggregateColumn<T> sum() {
		return this.createAggregation(Aggregation.sum);
	}

	@Override
	public final Group xbar(final LongValue xbar) {
		return new XbarGroupImpl<T>(xbar, this);
	}

	@Override
	public final Filter filterGreaterThan(final Value<J, T> value) {
		return this.createFilter(FilterComparator.greater, value);
	}

	@Override
	public final Filter filterGreaterOrEqualThan(final Value<J, T> value) {
		return this.createFilter(FilterComparator.greaterOrEqual, value);
	}

	@Override
	public final Filter filterSmallerThan(final Value<J, T> value) {
		return this.createFilter(FilterComparator.smaller, value);
	}

	@Override
	public final Filter filterSmallerOrEqualThan(final Value<J, T> value) {
		return this.createFilter(FilterComparator.smallerOrEqual, value);
	}

	@Override
	public final Filter filterGreaterThan(final Column<T> column) {
		return this.createFilter(FilterComparator.greater, column);
	}

	@Override
	public final Filter filterGreaterOrEqualThan(final Column<T> column) {
		return this.createFilter(FilterComparator.greaterOrEqual, column);
	}

	@Override
	public final Filter filterSmallerThan(final Column<T> column) {
		return this.createFilter(FilterComparator.smaller, column);
	}

	@Override
	public final Filter filterSmallerOrEqualThan(final Column<T> column) {
		return this.createFilter(FilterComparator.smallerOrEqual, column);
	}

}
