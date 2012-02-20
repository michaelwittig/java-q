package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.ASimpleOrdinalColumn;
import de.cinovo.q.query.type.impl.TypeFloat;

/**
 * Float column.
 *
 * @author mwittig
 *
 */
public class FloatColumn extends ASimpleOrdinalColumn<TypeFloat> {

	/**
	 * @param name Name
	 */
	public FloatColumn(final String name) {
		super(name, TypeFloat.class);
	}

}
