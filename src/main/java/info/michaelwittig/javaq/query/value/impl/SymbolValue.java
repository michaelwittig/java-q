package info.michaelwittig.javaq.query.value.impl;

import info.michaelwittig.javaq.query.type.Type;
import info.michaelwittig.javaq.query.type.impl.TypeList;
import info.michaelwittig.javaq.query.type.impl.TypeSymbol;

import java.util.Collection;

/**
 * Symbol value.
 * 
 * @author mwittig
 * 
 */
public final class SymbolValue extends AValue<String, TypeSymbol> {
	
	/** Null. */
	public static final String NULL = "`";
	
	
	/**
	 * @param value Value
	 * @return Symbol
	 */
	public static SymbolValue from(final String value) {
		return new SymbolValue(value, TypeSymbol.get());
	}
	
	/**
	 * @param values Values
	 * @return List of symbols
	 */
	public static ListValue<String, TypeList<String, Type<String>>> froms(final String... values) {
		return new ListValue<String, TypeList<String, Type<String>>>(values, TypeList.getSymbol());
	}
	
	/**
	 * @param values Values
	 * @return List of symbols
	 */
	public static ListValue<String, TypeList<String, Type<String>>> froms(final Collection<String> values) {
		return new ListValue<String, TypeList<String, Type<String>>>(values.toArray(new String[values.size()]), TypeList.getSymbol());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private SymbolValue(final String value, final TypeSymbol type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return SymbolValue.NULL;
		}
		return "`" + this.get();
	}
	
}
