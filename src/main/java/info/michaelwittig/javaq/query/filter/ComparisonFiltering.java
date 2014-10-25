package info.michaelwittig.javaq.query.filter;

import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.value.Value;

/**
 * Filter for comparison.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public interface ComparisonFiltering<J, T extends OrdinalType<J>> {
	
	/**
	 * @param value Value
	 * @return Filter(> value)
	 */
	Filter filterGreaterThan(final Value<J, T> value);
	
	/**
	 * @param value Value
	 * @return Filter(>= value)
	 */
	Filter filterGreaterOrEqualThan(final Value<J, T> value);
	
	/**
	 * @param value Value
	 * @return Filter(< value)
	 */
	Filter filterSmallerThan(final Value<J, T> value);
	
	/**
	 * @param value Value
	 * @return Filter(<= value)
	 */
	Filter filterSmallerOrEqualThan(final Value<J, T> value);
	
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
