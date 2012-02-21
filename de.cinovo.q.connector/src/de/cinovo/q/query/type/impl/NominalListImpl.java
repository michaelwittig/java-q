package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.NominalList;
import de.cinovo.q.query.type.NominalType;
import de.cinovo.q.query.type.TypeFactory;

/**
 * Nominal list implementation.
 *
 * @author mwittig
 *
 * @param <E> Type of type
 * @param <T> Type
 */
public final class NominalListImpl<E, T extends NominalType<E>> extends AListImpl<E, T> implements NominalList<E, T> {


	/**
	 * @param aValues Values
	 * @param aTypeFactory Type factory
	 */
	NominalListImpl(final E[] aValues, final TypeFactory<E, T> aTypeFactory) {
		super(aValues, aTypeFactory);
	}


}
