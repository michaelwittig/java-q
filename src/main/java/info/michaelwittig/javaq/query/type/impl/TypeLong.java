package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.LongValue;

/**
 * Long.
 * 
 * @author mwittig
 * 
 */
public final class TypeLong implements OrdinalType<Long> {
	
	/** Instance. */
	private static final TypeLong INSTANCE = new TypeLong();
	
	
	/**
	 * @return Instance
	 */
	public static TypeLong get() {
		return TypeLong.INSTANCE;
	}
	
	@Override
	public ValueFactory<Long, Type<Long>> geValueFactory() {
		return new ValueFactory<Long, Type<Long>>() {
			
			@Override
			public Value<Long, ? extends Type<Long>> create(final Long value) {
				return LongValue.from(value);
			}
			
			@Override
			public Value<Long, ? extends Type<Long>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Long) {
					return this.create((Long) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
			
		};
	}
	
	/** */
	private TypeLong() {
		super();
	}
	
}
