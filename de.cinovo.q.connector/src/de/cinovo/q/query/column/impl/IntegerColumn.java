package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.type.impl.TypeInteger;

/**
 * Integer column.
 *
 * @author mwittig
 *
 */
public class IntegerColumn extends ASimpleOrdinalColumn<TypeInteger> {

	/**
	 * @param name Name
	 */
	public IntegerColumn(final String name) {
		super(name, TypeInteger.class);
	}

}
