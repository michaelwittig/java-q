package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeInteger;

/**
 * Integer column.
 * 
 * @author mwittig
 * 
 */
public class IntegerColumn extends ASimpleOrdinalColumn<Integer, TypeInteger> {
	
	/**
	 * @param name Name
	 */
	public IntegerColumn(final String name) {
		super(name, TypeInteger.get());
	}
	
}
