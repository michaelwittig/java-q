package de.cinovo.q.query.type;

import de.cinovo.q.query.value.Value;


/**
 * Type factory.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public interface ValueFactory<J, T extends Type<J>> {

	/**
	 * @param value Value
	 * @return Value
	 */
	Value<J, ? extends T> create(J value);

	/**
	 * @param value Value
	 * @return Value
	 */
	Value<J, ? extends T> fromQ(Object value);

}
