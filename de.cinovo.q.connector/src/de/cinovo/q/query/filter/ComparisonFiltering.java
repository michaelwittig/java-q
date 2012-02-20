package de.cinovo.q.query.filter;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.type.OrdinalType;

/**
 * Filter for comparison.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public interface ComparisonFiltering<T extends OrdinalType<?>> {

	/**
	 * @param value Value
	 * @return Filter(> value)
	 */
	Filter filterGreaterThan(final T value);

	/**
	 * @param value Value
	 * @return Filter(>= value)
	 */
	Filter filterGreaterOrEqualThan(final T value);

	/**
	 * @param value Value
	 * @return Filter(< value)
	 */
	Filter filterSmallerThan(final T value);

	/**
	 * @param value Value
	 * @return Filter(<= value)
	 */
	Filter filterSmallerOrEqualThan(final T value);

	/**
	 * @param column Column
	 * @return Filter(> column)
	 */
	Filter filterGreaterThan(final Column<T> column);

	/**
	 * @param column Column
	 * @return Filter(>= column)
	 */
	Filter filterGreaterOrEqualThan(final Column<T> column);

	/**
	 * @param column Column
	 * @return Filter(< column)
	 */
	Filter filterSmallerThan(final Column<T> column);

	/**
	 * @param column Column
	 * @return Filter(<= column)
	 */
	Filter filterSmallerOrEqualThan(final Column<T> column);

}
