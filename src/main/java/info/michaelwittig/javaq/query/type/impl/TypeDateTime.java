package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.DateTimeValue;

import java.util.Date;

/**
 * DateTime.
 * 
 * @author mwittig
 * 
 */
public final class TypeDateTime implements OrdinalType<Date> {
	
	/** Instance. */
	private static final TypeDateTime INSTANCE = new TypeDateTime();
	
	
	/**
	 * @return Instance
	 */
	public static TypeDateTime get() {
		return TypeDateTime.INSTANCE;
	}
	
	@Override
	public ValueFactory<Date, Type<Date>> geValueFactory() {
		return new ValueFactory<Date, Type<Date>>() {
			
			@Override
			public Value<Date, ? extends Type<Date>> create(final Date value) {
				return DateTimeValue.from(value);
			}
			
			@Override
			public Value<Date, ? extends Type<Date>> fromQ(final Object aValue) {
				if (aValue == null) {
					return this.create(null);
				}
				if (aValue instanceof Date) {
					return this.create((Date) aValue);
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
			
		};
	}
	
	/**  */
	private TypeDateTime() {
		super();
	}
	
}
