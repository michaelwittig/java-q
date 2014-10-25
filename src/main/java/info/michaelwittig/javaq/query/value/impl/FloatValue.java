package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeFloat;
import info.michaelwittig.javaq.query.type.impl.TypeList;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Float value.
 * 
 * @author mwittig
 * 
 */
public final class FloatValue extends AValue<BigDecimal, TypeFloat> {
	
	/** Null. */
	public static final String NULL = "0n";
	
	
	/**
	 * @param value Value
	 * @return Float
	 */
	public static FloatValue from(final BigDecimal value) {
		return new FloatValue(value, TypeFloat.get());
	}
	
	/**
	 * @param values Values
	 * @return List of floats
	 */
	public static ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>> froms(final BigDecimal[] values) {
		return new ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>>(values, TypeList.getFloat());
	}
	
	/**
	 * @param values Values
	 * @return List of floats
	 */
	public static ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>> froms(final Collection<BigDecimal> values) {
		return new ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>>(values.toArray(new BigDecimal[values.size()]), TypeList.getFloat());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private FloatValue(final BigDecimal value, final TypeFloat type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return FloatValue.NULL;
		}
		return this.get() + "f";
	}
	
}
