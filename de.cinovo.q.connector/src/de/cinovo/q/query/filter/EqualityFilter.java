package de.cinovo.q.query.filter;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.type.NominalType;

/**
 * Filter for equality.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public interface EqualityFilter<T extends NominalType<?>> {

	/**
	 * @param value Value
	 * @return Filter(= value)
	 */
	Filter filterEqualTo(final T value);

	/**
	 * @param value Value
	 * @return Filter(<> value)
	 */
	Filter filterNotEqualTo(final T value);

	/**
	 * @param column Column
	 * @return Filter(= column)
	 */
	Filter filterEqualTo(final Column<T> column);

	/**
	 * @param column Column
	 * @return Filter(<> column)
	 */
	Filter filterNotEqualTo(final Column<T> column);

}
