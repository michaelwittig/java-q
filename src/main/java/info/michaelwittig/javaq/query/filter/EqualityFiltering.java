package info.michaelwittig.javaq.query.filter;

import info.michaelwittig.javaq.query.column.Column;
import info.michaelwittig.javaq.query.type.NominalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.ListValue;

/**
 * Filter for equality.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public interface EqualityFiltering<J, T extends NominalType<J>> {
	
	/**
	 * @param value Value
	 * @return Filter(= value)
	 */
	Filter filterEqualTo(final Value<J, T> value);
	
	/**
	 * @param value Value
	 * @return Filter(<> value)
	 */
	Filter filterNotEqualTo(final Value<J, T> value);
	
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
	
	/**
	 * @param list List
	 * @return Filter(in list)
	 */
	Filter filterIn(final ListValue<J, TypeList<J, Type<J>>> list);
	
}
