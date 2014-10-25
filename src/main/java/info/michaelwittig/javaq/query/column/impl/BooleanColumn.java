package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeBoolean;

/**
 * Boolean column.
 * 
 * @author mwittig
 * 
 */
public class BooleanColumn extends ASimpleOrdinalColumn<Boolean, TypeBoolean> {
	
	/**
	 * @param name Name
	 */
	public BooleanColumn(final String name) {
		super(name, TypeBoolean.get());
	}
	
}
