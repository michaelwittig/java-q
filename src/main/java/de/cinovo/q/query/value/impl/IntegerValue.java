package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.impl.TypeInteger;

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
	public static OrdinalList<Integer, TypeInteger> froms(final Integer[] values) {
		return new OrdinalListImpl<Integer, TypeInteger>(values, TypeInteger.get());
	}

	/**
	 * @param value Value
	 * @param type Type
	 */
	public IntegerValue(final Integer value, final TypeInteger type) {
		super(value, type);
	}

	@Override
	public String toQ() {
		if (this.get() == null) {
			return NULL;
		}
		return String.valueOf(this.get());
	}

}
