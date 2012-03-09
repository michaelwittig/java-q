package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.NominalList;
import de.cinovo.q.query.type.impl.TypeSymbol;

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
	public static NominalList<String, TypeSymbol> froms(final String[] values) {
		return new NominalListImpl<String, TypeSymbol>(values, TypeSymbol.get());
	}

	/**
	 * @param value Value
	 * @param type Type
	 */
	public SymbolValue(final String value, final TypeSymbol type) {
		super(value, type);
	}

	@Override
	public String toQ() {
		if (this.get() == null) {
			return NULL;
		}
		return "`" + this.get();
	}

}
