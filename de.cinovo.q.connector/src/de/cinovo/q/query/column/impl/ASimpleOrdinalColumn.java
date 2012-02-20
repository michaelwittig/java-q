package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.filter.ComparisonFiltering;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.filter.impl.FilterComparator;
import de.cinovo.q.query.group.Group;
import de.cinovo.q.query.group.XbarGrouping;
import de.cinovo.q.query.group.impl.XbarGroupImpl;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.impl.TypeInteger;

/**
 * Abstract simple ordinal column.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public abstract class ASimpleOrdinalColumn<T extends OrdinalType<?>> extends ASimpleNominalColumn<T> implements ComparisonFiltering<T>, XbarGrouping<T> {

	/**
	 * @param aName Name
	 * @param aType Type
	 */
	public ASimpleOrdinalColumn(final String aName, final Class<T> aType) {
		super(aName, aType);
	}

	@Override
	public final Group xbar(final TypeInteger xbar) {
		return new XbarGroupImpl<T>(xbar, this);
	}

	@Override
	public final Filter filterGreaterThan(final T value) {
		return this.createFilter(FilterComparator.greater, value);
	}

	@Override
	public final Filter filterGreaterOrEqualThan(final T value) {
		return this.createFilter(FilterComparator.greaterOrEqual, value);
	}

	@Override
	public final Filter filterSmallerThan(final T value) {
		return this.createFilter(FilterComparator.smaller, value);
	}

	@Override
	public final Filter filterSmallerOrEqualThan(final T value) {
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
