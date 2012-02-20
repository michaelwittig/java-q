package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.NominalType;


/**
 * Symbol.
 *
 * @author mwittig
 *
 */
public final class TypeSymbol implements NominalType<String> {

	/** Null. */
	public static final String NULL = "`";

	/**
	 * @param value Value
	 * @return Symbol
	 */
	public static TypeSymbol from(final String value) {
		return new TypeSymbol(value);
	}

	/** Value. */
	private final String value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return "`" + this.value;
	}

	/**
	 * @param aValue Value
	 */
	private TypeSymbol(final String aValue) {
		this.value = aValue;
	}

}
