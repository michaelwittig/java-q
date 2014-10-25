package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeDate;

import java.sql.Date;

/**
 * Date column.
 * 
 * @author mwittig
 * 
 */
public class DateColumn extends ASimpleOrdinalColumn<Date, TypeDate> {
	
	/**
	 * @param name Name
	 */
	public DateColumn(final String name) {
		super(name, TypeDate.get());
	}
	
}
