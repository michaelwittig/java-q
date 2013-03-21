// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.AggregateColumn;
import de.cinovo.q.query.column.AggregatingNominal;
import de.cinovo.q.query.column.Aggregation;
import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.column.VirtualColumn;
import de.cinovo.q.query.column.Virtualling;
import de.cinovo.q.query.filter.EqualityFiltering;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.filter.impl.FilterComparator;
import de.cinovo.q.query.filter.impl.FilterImpl;
import de.cinovo.q.query.group.Group;
import de.cinovo.q.query.group.Grouping;
import de.cinovo.q.query.type.List;
import de.cinovo.q.query.type.NominalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeList;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.ListValue;

/**
 * Abstract simple nominal column.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public abstract class ASimpleNominalColumn<J, T extends NominalType<J>> implements Column<T>, EqualityFiltering<J, T>, Virtualling<T>, AggregatingNominal<T>, Grouping {
	
	/** Name. */
	private final String name;
	
	/** Type. */
	private final T type;
	
	
	/**
	 * @param aName Name
	 * @param aType Type
	 */
	protected ASimpleNominalColumn(final String aName, final T aType) {
		super();
		this.name = aName;
		this.type = aType;
	}
	
	@Override
	public final String toQ() {
		return this.name;
	}
	
	@Override
	public final String getName() {
		return this.name;
	}
	
	@Override
	public final T getType() {
		return this.type;
	}
	
	@Override
	public final VirtualColumn<T> virtual(final String virtual) {
		return new VirtualColumnImpl<T>(virtual, this);
	}
	
	@Override
	public final AggregateColumn<T> first() {
		return this.createAggregation(Aggregation.first);
	}
	
	@Override
	public final AggregateColumn<T> last() {
		return this.createAggregation(Aggregation.last);
	}
	
	@Override
	public final AggregateColumn<T> count() {
		return this.createAggregation(Aggregation.count);
	}
	
	/**
	 * @param aggregation Aggregation
	 * @return Filter
	 */
	protected final AggregateColumn<T> createAggregation(final Aggregation aggregation) {
		return new AggregateColumnImpl<T>(aggregation, this);
	}
	
	@Override
	public final Group group() {
		return new Group() {
			
			@Override
			public String toQ() {
				return ASimpleNominalColumn.this.toQ();
			}
			
			@Override
			public Column<T> getColumn() {
				return ASimpleNominalColumn.this;
			}
		};
	}
	
	@Override
	public final Filter filterEqualTo(final Value<J, T> value) {
		return this.createFilter(FilterComparator.equal, value);
	}
	
	@Override
	public final Filter filterNotEqualTo(final Value<J, T> value) {
		return this.createFilter(FilterComparator.notEqua, value);
	}
	
	@Override
	public final Filter filterEqualTo(final Column<T> column) {
		return this.createFilter(FilterComparator.equal, column);
	}
	
	@Override
	public final Filter filterNotEqualTo(final Column<T> column) {
		return this.createFilter(FilterComparator.notEqua, column);
	}
	
	@Override
	public final Filter filterIn(final ListValue<J, TypeList<J, Type<J>>> list) {
		return this.createFilter(FilterComparator.in, list);
	}
	
	/**
	 * @param comarator Comparator
	 * @param value Value
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final ListValue<J, TypeList<J, Type<J>>> value) {
		return new FilterImpl<J, T>(this, comarator, value);
	}

	/**
	 * @param comarator Comparator
	 * @param value Value
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final Value<J, T> value) {
		return new FilterImpl<J, T>(this, comarator, value);
	}
	
	/**
	 * @param comarator Comparator
	 * @param column Column
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final Column<T> column) {
		return new FilterImpl<J, T>(this, comarator, column);
	}
	
	/**
	 * @param comarator Comparator
	 * @param list List
	 * @return Filter
	 */
	protected final Filter createFilter(final FilterComparator comarator, final List<J, T> list) {
		return new FilterImpl<J, T>(this, comarator, list);
	}
	
}
