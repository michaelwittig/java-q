package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.ASimpleOrdinalColumn;
import de.cinovo.q.query.type.impl.TypeReal;

/**
 * Real column.
 *
 * @author mwittig
 *
 */
public class RealColumn extends ASimpleOrdinalColumn<TypeReal> {

	/**
	 * @param name Name
	 */
	public RealColumn(final String name) {
		super(name, TypeReal.class);
	}

}
