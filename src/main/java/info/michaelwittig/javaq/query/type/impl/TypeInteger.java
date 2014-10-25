package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.IntegerValue;

/**
 * Integer.
 * 
 * @author mwittig
 * 
 */
public final class TypeInteger implements OrdinalType<Integer> {
	
	/** Instance. */
	private static final TypeInteger INSTANCE = new TypeInteger();
	
	
	/**
	 * @return Instance
	 */
	public static TypeInteger get() {
		return TypeInteger.INSTANCE;
	}
	
	@Override
	public ValueFactory<Integer, Type<Integer>> geValueFactory() {
		return new ValueFactory<Integer, Type<Integer>>() {
			
			@Override
			public Value<Integer, ? extends Type<Integer>> create(final Integer value) {
				return IntegerValue.from(value);
			}
			
			@Override
			public Value<Integer, ? extends Type<Integer>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Integer) {
					return this.create((Integer) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeInteger() {
		super();
	}
	
}
