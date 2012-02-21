package de.cinovo.q.query.type;

/**
 * Type factory.
 *
 * @author mwittig
 *
 * @param <E> Type of type
 * @param <T> Type
 */
public interface TypeFactory<E, T extends Type<E>> {

	/**
	 * @param value Value
	 * @return Type
	 */
	T create(E value);

}
