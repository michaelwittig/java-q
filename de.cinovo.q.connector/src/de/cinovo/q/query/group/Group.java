package de.cinovo.q.query.group;

import de.cinovo.q.Q;
import de.cinovo.q.query.column.Column;

/**
 * Group.
 *
 * @author mwittig
 *
 */
public interface Group extends Q {

	/**
	 * @return Column
	 */
	Column<?> getColumn();

}
