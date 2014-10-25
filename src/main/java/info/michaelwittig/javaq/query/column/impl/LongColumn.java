package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeLong;

/**
 * Long column.
 * 
 * @author mwittig
 * 
 */
public class LongColumn extends ASimpleOrdinalColumn<Long, TypeLong> {
	
	/**
	 * @param name Name
	 */
	public LongColumn(final String name) {
		super(name, TypeLong.get());
	}
	
}
