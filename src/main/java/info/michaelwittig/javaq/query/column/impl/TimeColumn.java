package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeTime;

import java.sql.Time;

/**
 * Time column.
 * 
 * @author mwittig
 * 
 */
public class TimeColumn extends ASimpleOrdinalColumn<Time, TypeTime> {
	
	/**
	 * @param name Name
	 */
	public TimeColumn(final String name) {
		super(name, TypeTime.get());
	}
	
}
