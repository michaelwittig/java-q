package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.RealValue;

import java.math.BigDecimal;

/**
 * Real.
 * 
 * @author mwittig
 * 
 */
public final class TypeReal implements OrdinalType<BigDecimal> {
	
	/** Instance. */
	private static final TypeReal INSTANCE = new TypeReal();
	
	
	/**
	 * @return Instance
	 */
	public static TypeReal get() {
		return TypeReal.INSTANCE;
	}
	
	@Override
	public ValueFactory<BigDecimal, Type<BigDecimal>> geValueFactory() {
		return new ValueFactory<BigDecimal, Type<BigDecimal>>() {
			
			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> create(final BigDecimal aValue) {
				return RealValue.from(aValue);
			}
			
			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> fromQ(final Object aValue) {
				if (aValue == null) {
					return this.create(null);
				}
				if (aValue instanceof Double) {
					return this.create(new BigDecimal((Double) aValue));
				}
				if (aValue instanceof Float) {
					return this.create(new BigDecimal((Float) aValue));
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
		};
	}
	
	/**  */
	private TypeReal() {
		super();
	}
	
}
