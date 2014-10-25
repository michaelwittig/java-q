package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeInteger;
import info.michaelwittig.javaq.query.type.impl.TypeList;

import java.util.Collection;

/**
 * Integer value.
 * 
 * @author mwittig
 * 
 */
public final class IntegerValue extends AValue<Integer, TypeInteger> {
	
	/** Null. */
	public static final String NULL = "0N";
	
	
	/**
	 * @param value Value
	 * @return Integer
	 */
	public static IntegerValue from(final Integer value) {
		return new IntegerValue(value, TypeInteger.get());
	}
	
	/**
	 * @param values Values
	 * @return List of integers
	 */
	public static ListValue<Integer, TypeList<Integer, Type<Integer>>> froms(final Integer[] values) {
		return new ListValue<Integer, TypeList<Integer, Type<Integer>>>(values, TypeList.getInteger());
	}
	
	/**
	 * @param values Values
	 * @return List of integers
	 */
	public static ListValue<Integer, TypeList<Integer, Type<Integer>>> froms(final Collection<Integer> values) {
		return new ListValue<Integer, TypeList<Integer, Type<Integer>>>(values.toArray(new Integer[values.size()]), TypeList.getInteger());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private IntegerValue(final Integer value, final TypeInteger type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return IntegerValue.NULL;
		}
		return String.valueOf(this.get());
	}
	
}
