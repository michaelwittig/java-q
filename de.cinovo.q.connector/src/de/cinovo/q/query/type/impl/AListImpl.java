package de.cinovo.q.query.type.impl;

import java.util.ArrayList;

import de.cinovo.q.query.type.List;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.TypeFactory;

/**
 * Abstract list implementation.
 *
 * @author mwittig
 *
 * @param <E> Type of type
 * @param <T> Type
 */
public abstract class AListImpl<E, T extends Type<E>> implements List<E, T> {

	/** Values. */
	private final ArrayList<T> values = new ArrayList<T>();

	/**
	 * @param aValues Values
	 * @param aTypeFactory Type factory
	 */
	protected AListImpl(final E[] aValues, final TypeFactory<E, T> aTypeFactory) {
		for (final E value : aValues) {
			this.values.add(aTypeFactory.create(value));
		}
	}

	@Override
	public final String toQ() {
		final StringBuilder sb = new StringBuilder();
		sb.append("(");
		if (this.values.size() > 0) {
			for (final T value : this.values) {
				sb.append(value.toQ());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(")");
		return sb.toString();
	}

}
