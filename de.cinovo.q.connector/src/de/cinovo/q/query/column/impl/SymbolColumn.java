package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.ASimpleNominalColumn;
import de.cinovo.q.query.type.impl.TypeSymbol;

/**
 * Symbol column.
 *
 * @author mwittig
 *
 */
public class SymbolColumn extends ASimpleNominalColumn<TypeSymbol> {

	/**
	 * @param name Name
	 */
	public SymbolColumn(final String name) {
		super(name, TypeSymbol.class);
	}

}
