package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.FloatValue;

import java.math.BigDecimal;

/**
 * Float.
 * 
 * @author mwittig
 * 
 */
public final class TypeFloat implements OrdinalType<BigDecimal> {
	
	/** Instance. */
	private static final TypeFloat INSTANCE = new TypeFloat();
	
	
	/**
	 * @return Instance
	 */
	public static TypeFloat get() {
		return TypeFloat.INSTANCE;
	}
	
	@Override
	public ValueFactory<BigDecimal, Type<BigDecimal>> geValueFactory() {
		return new ValueFactory<BigDecimal, Type<BigDecimal>>() {
			
			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> create(final BigDecimal value) {
				return FloatValue.from(value);
			}
			
			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Float) {
					return this.create(new BigDecimal((Float) value));
				}
				if (value instanceof Double) {
					return this.create(new BigDecimal((Double) value));
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeFloat() {
		super();
	}
	
}
