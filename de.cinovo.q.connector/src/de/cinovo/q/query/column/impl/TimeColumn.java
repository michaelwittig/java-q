package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.type.impl.TypeTime;

/**
 * Time column.
 *
 * @author mwittig
 *
 */
public class TimeColumn extends ASimpleOrdinalColumn<TypeTime> {

	/**
	 * @param name Name
	 */
	public TimeColumn(final String name) {
		super(name, TypeTime.class);
	}

}
