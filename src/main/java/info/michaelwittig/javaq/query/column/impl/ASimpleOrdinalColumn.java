package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.column.AggregateColumn;
import info.michaelwittig.javaq.query.column.AggregatingOrdinal;
import info.michaelwittig.javaq.query.column.Aggregation;
import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.filter.ComparisonFiltering;
import info.michaelwittig.javaq.query.filter.Filter;
import info.michaelwittig.javaq.query.filter.impl.FilterComparator;
import info.michaelwittig.javaq.query.group.Group;
import info.michaelwittig.javaq.query.group.XbarGrouping;
import info.michaelwittig.javaq.query.group.impl.XbarGroupImpl;
import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Abstract simple ordinal column.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public abstract class ASimpleOrdinalColumn<J, T extends OrdinalType<J>> extends ASimpleNominalColumn<J, T> implements ComparisonFiltering<J, T>, XbarGrouping<J, T>, AggregatingOrdinal<T> {
	
	/**
	 * @param aName Name
	 * @param aType Type
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
	public final Group xbar(final Value<J, T> xbar) {
		return new XbarGroupImpl<J, T>(xbar, this);
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
