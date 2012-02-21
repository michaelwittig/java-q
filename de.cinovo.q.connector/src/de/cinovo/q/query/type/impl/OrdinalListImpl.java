package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.TypeFactory;

/**
 * Ordinal list implementation.
 *
 * @author mwittig
 *
 * @param <E> Type of type
 * @param <T> Type
 */
public final class OrdinalListImpl<E, T extends OrdinalType<E>> extends AListImpl<E, T> implements OrdinalList<E, T> {

	/**
	 * @param aValues Values
	 * @param aTypeFactory Type factory
	 */
	OrdinalListImpl(final E[] aValues, final TypeFactory<E, T> aTypeFactory) {
		super(aValues, aTypeFactory);
	}

}
