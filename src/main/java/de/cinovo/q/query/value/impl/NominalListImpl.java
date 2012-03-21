package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.NominalList;
import de.cinovo.q.query.type.NominalType;

/**
 * Nominal list implementation.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public final class NominalListImpl<J, T extends NominalType<J>> extends AListImpl<J, T> implements NominalList<J, T> {


	/**
	 * @param aValues Values
	 * @param aType Type
	 */
	NominalListImpl(final J[] aValues, final T aType) {
		super(aValues, aType);
	}


}
