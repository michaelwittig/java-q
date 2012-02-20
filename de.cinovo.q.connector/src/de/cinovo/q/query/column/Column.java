package de.cinovo.q.query.column;

import de.cinovo.q.Q;
import de.cinovo.q.query.type.Type;

/**
 * Column.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public interface Column<T extends Type<?>> extends Q {

	/**
	 * @return Name
	 */
	String getName();

	/**
	 * @return Type
	 */
	Class<T> getType();

}
