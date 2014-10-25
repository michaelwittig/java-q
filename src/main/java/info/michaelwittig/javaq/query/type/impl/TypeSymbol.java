package info.michaelwittig.javaq.query.type.impl;

import info.michaelwittig.javaq.query.type.NominalType;
import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.ValueFactory;
import info.michaelwittig.javaq.query.value.Value;
import info.michaelwittig.javaq.query.value.impl.SymbolValue;

/**
 * Symbol.
 * 
 * @author mwittig
 * 
 */
public final class TypeSymbol implements NominalType<String> {
	
	/** Instance. */
	private static final TypeSymbol INSTANCE = new TypeSymbol();
	
	
	/**
	 * @return Instance
	 */
	public static TypeSymbol get() {
		return TypeSymbol.INSTANCE;
	}
	
	@Override
	public ValueFactory<String, Type<String>> geValueFactory() {
		return new ValueFactory<String, Type<String>>() {
			
			@Override
			public Value<String, ? extends Type<String>> create(final String value) {
				return SymbolValue.from(value);
			}
			
			@Override
			public Value<String, ? extends Type<String>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof String) {
					return this.create((String) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/**  */
	private TypeSymbol() {
		super();
	}
	
}
