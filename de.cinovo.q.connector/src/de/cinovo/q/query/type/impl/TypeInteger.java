package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.OrdinalType;


/**
 * Integer.
 *
 * @author mwittig
 *
 */
public final class TypeInteger implements OrdinalType<Integer> {

	/** Null. */
	public static final String NULL = "0N";

	/**
	 * @param value Value
	 * @return Integer
	 */
	public static TypeInteger from(final Integer value) {
		return new TypeInteger(value);
	}

	/** Value. */
	private final Integer value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return String.valueOf(this.value);
	}

	/**
	 * @param aValue Value
	 */
	private TypeInteger(final Integer aValue) {
		this.value = aValue;
	}

}
