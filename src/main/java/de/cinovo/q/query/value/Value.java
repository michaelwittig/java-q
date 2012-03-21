package de.cinovo.q.query.value;

import de.cinovo.q.Q;
import de.cinovo.q.query.type.Type;

/**
 * Value.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public interface Value<J, T extends Type<J>> extends Q {

	/**
	 * @return Value
	 */
	J get();

	/**
	 * @return Type
	 */
	T getType();

}
