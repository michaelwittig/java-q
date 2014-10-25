package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.TimeValue;

import java.sql.Time;

/**
 * Time.
 * 
 * @author mwittig
 * 
 */
public final class TypeTime implements OrdinalType<Time> {
	
	/** Instance. */
	private static final TypeTime INSTANCE = new TypeTime();
	
	
	/**
	 * @return Instance
	 */
	public static TypeTime get() {
		return TypeTime.INSTANCE;
	}
	
	@Override
	public ValueFactory<Time, Type<Time>> geValueFactory() {
		return new ValueFactory<Time, Type<Time>>() {
			
			@Override
			public Value<Time, ? extends Type<Time>> create(final Time value) {
				return TimeValue.from(value);
			}
			
			@Override
			public Value<Time, ? extends Type<Time>> fromQ(final Object aValue) {
				if (aValue == null) {
					return this.create(null);
				}
				if (aValue instanceof Time) {
					return this.create((Time) aValue);
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
			
		};
	}
	
	/**  */
	private TypeTime() {
		super();
	}
	
}
