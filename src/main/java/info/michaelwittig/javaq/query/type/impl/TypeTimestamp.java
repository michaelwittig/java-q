package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.TimestampValue;

import java.sql.Timestamp;

/**
 * Timestamp.
 * 
 * @author mwittig
 * 
 */
public final class TypeTimestamp implements OrdinalType<Timestamp> {
	
	/** Instance. */
	private static final TypeTimestamp INSTANCE = new TypeTimestamp();
	
	
	/**
	 * @return Instance
	 */
	public static TypeTimestamp get() {
		return TypeTimestamp.INSTANCE;
	}
	
	@Override
	public ValueFactory<Timestamp, Type<Timestamp>> geValueFactory() {
		return new ValueFactory<Timestamp, Type<Timestamp>>() {
			
			@Override
			public Value<Timestamp, ? extends Type<Timestamp>> create(final Timestamp value) {
				return TimestampValue.from(value);
			}
			
			@Override
			public Value<Timestamp, ? extends Type<Timestamp>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Timestamp) {
					return this.create((Timestamp) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeTimestamp() {
		super();
	}
	
}
