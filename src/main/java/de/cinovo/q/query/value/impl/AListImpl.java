package de.cinovo.q.query.value.impl;

import java.util.ArrayList;

import de.cinovo.q.query.type.List;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;

/**
 * Abstract list implementation.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public abstract class AListImpl<J, T extends Type<J>> implements List<J, T> {

	/** Values. */
	private final ArrayList<Value<J, ? extends Type<J>>> values = new ArrayList<Value<J, ? extends Type<J>>>();

	/**
	 * @param aValues Values
	 * @param aType Type
	 */
	protected AListImpl(final J[] aValues, final T aType) {
		for (final J value : aValues) {
			this.values.add(aType.geValueFactory().create(value));
		}
	}

	@Override
	public final String toQ() {
		final StringBuilder sb = new StringBuilder();
		sb.append("(");
		if (this.values.size() > 0) {
			for (final Value<J, ? extends Type<J>> value : this.values) {
				sb.append(value.toQ());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public final ValueFactory<J, Type<J>> geValueFactory() {
		throw new UnsupportedOperationException();
	}

}
