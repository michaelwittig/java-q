package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.ASimpleOrdinalColumn;
import de.cinovo.q.query.type.impl.TypeTimestamp;

/**
 * Timestamp column.
 *
 * @author mwittig
 *
 */
public class TimestampColumn extends ASimpleOrdinalColumn<TypeTimestamp> {

	/**
	 * @param name Name
	 */
	public TimestampColumn(final String name) {
		super(name, TypeTimestamp.class);
	}

}
