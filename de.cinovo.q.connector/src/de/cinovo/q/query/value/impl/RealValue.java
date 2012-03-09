package de.cinovo.q.query.value.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.impl.TypeReal;

/**
 * Real value.
 *
 * @author mwittig
 *
 */
public final class RealValue extends AValue<BigDecimal, TypeReal> {

	/** Null. */
	public static final String NULL = "0Ne";

	/**
	 * @param value Value
	 * @return Real
	 */
	public static RealValue from(final BigDecimal value) {
		return new RealValue(value, TypeReal.get());
	}

	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static OrdinalList<BigDecimal, TypeReal> froms(final BigDecimal[] values) {
		return new OrdinalListImpl<BigDecimal, TypeReal>(values, TypeReal.get());
	}

	/**
	 * @param value Value
	 * @param type Type
	 */
	public RealValue(final BigDecimal value, final TypeReal type) {
		super(value, type);
	}

	@Override
	public String toQ() {
		if (this.get() == null) {
			return NULL;
		}
		return this.get() + "e";
	}

}
