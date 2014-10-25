package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.type.impl.TypeReal;

import java.math.BigDecimal;
import java.util.Collection;

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
	public static ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>> froms(final BigDecimal[] values) {
		return new ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>>(values, TypeList.getReal());
	}
	
	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>> froms(final Collection<BigDecimal> values) {
		return new ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>>(values.toArray(new BigDecimal[values.size()]), TypeList.getReal());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private RealValue(final BigDecimal value, final TypeReal type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return RealValue.NULL;
		}
		return this.get() + "e";
	}
	
}
