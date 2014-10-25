package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.OrdinalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.DateValue;

import java.sql.Date;

/**
 * Date.
 * 
 * @author mwittig
 * 
 */
public final class TypeDate implements OrdinalType<Date> {
	
	/** Instance. */
	private static final TypeDate INSTANCE = new TypeDate();
	
	
	/**
	 * @return Instance
	 */
	public static TypeDate get() {
		return TypeDate.INSTANCE;
	}
	
	@Override
	public ValueFactory<Date, Type<Date>> geValueFactory() {
		return new ValueFactory<Date, Type<Date>>() {
			
			@Override
			public Value<Date, ? extends Type<Date>> create(final Date value) {
				return DateValue.from(value);
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
	private TypeDate() {
		super();
	}
	
}
