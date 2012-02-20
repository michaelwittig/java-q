package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.type.impl.TypeLong;

/**
 * Long column.
 *
 * @author mwittig
 *
 */
public class LongColumn extends ASimpleOrdinalColumn<TypeLong> {

	/**
	 * @param name Name
	 */
	public LongColumn(final String name) {
		super(name, TypeLong.class);
	}

}
