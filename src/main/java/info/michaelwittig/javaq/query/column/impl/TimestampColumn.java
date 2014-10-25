package info.michaelwittig.javaq.query.column.impl;

import info.michaelwittig.javaq.query.type.impl.TypeTimestamp;

import java.sql.Timestamp;

/**
 * Timestamp column.
 * 
 * @author mwittig
 * 
 */
public class TimestampColumn extends ASimpleOrdinalColumn<Timestamp, TypeTimestamp> {
	
	/**
	 * @param name Name
	 */
	public TimestampColumn(final String name) {
		super(name, TypeTimestamp.get());
	}
	
}
