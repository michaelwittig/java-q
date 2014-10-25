package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.BooleanValue;

/**
 * Boolean.
 * 
 * @author mwittig
 * 
 */
public final class TypeBoolean implements OrdinalType<Boolean> {
	
	/** Instance. */
	private static final TypeBoolean INSTANCE = new TypeBoolean();
	
	
	/**
	 * @return Instance
	 */
	public static TypeBoolean get() {
		return TypeBoolean.INSTANCE;
	}
	
	@Override
	public ValueFactory<Boolean, Type<Boolean>> geValueFactory() {
		return new ValueFactory<Boolean, Type<Boolean>>() {
			
			@Override
			public Value<Boolean, ? extends Type<Boolean>> create(final Boolean value) {
				return BooleanValue.from(value);
			}
			
			@Override
			public Value<Boolean, ? extends Type<Boolean>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Boolean) {
					return this.create((Boolean) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeBoolean() {
		super();
	}
	
}
