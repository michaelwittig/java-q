package de.cinovo.q.query.filter;

import de.cinovo.q.Q;
import de.cinovo.q.query.column.Column;

/**
 * Filter.
 *
 * @author mwittig
 *
 */
public interface Filter extends Q {

	/**
	 * @return Column
	 */
	Column<?> getColumn();

}
