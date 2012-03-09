package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.OrdinalType;

/**
 * Ordinal list implementation.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public final class OrdinalListImpl<J, T extends OrdinalType<J>> extends AListImpl<J, T> implements OrdinalList<J, T> {

	/**
	 * @param aValues Values
	 * @param aType Type
	 */
	OrdinalListImpl(final J[] aValues, final T aType) {
		super(aValues, aType);
	}

}
