package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeDateTime;

import java.util.Date;

/**
 * DateTime column.
 * 
 * @author mwittig
 * 
 */
public class DateTimeColumn extends ASimpleOrdinalColumn<Date, TypeDateTime> {
	
	/**
	 * @param name Name
	 */
	public DateTimeColumn(final String name) {
		super(name, TypeDateTime.get());
	}
	
}
