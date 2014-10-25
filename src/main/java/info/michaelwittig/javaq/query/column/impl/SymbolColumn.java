package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeSymbol;

/**
 * Symbol column.
 * 
 * @author mwittig
 * 
 */
public class SymbolColumn extends ASimpleNominalColumn<String, TypeSymbol> {
	
	/**
	 * @param name Name
	 */
	public SymbolColumn(final String name) {
		super(name, TypeSymbol.get());
	}
	
}
