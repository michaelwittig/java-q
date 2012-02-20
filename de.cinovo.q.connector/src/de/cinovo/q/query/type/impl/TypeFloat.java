package de.cinovo.q.query.type.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.OrdinalType;


/**
 * Float.
 *
 * @author mwittig
 *
 */
public final class TypeFloat implements OrdinalType<BigDecimal> {

	/** Null. */
	public static final String NULL = "0n";

	/**
	 * @param value Value
	 * @return Real
	 */
	public static TypeFloat from(final BigDecimal value) {
		return new TypeFloat(value);
	}

	/** Value. */
	private final BigDecimal value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return this.value + "f";
	}

	/**
	 * @param aValue Value
	 */
	private TypeFloat(final BigDecimal aValue) {
		this.value = aValue;
	}

}
