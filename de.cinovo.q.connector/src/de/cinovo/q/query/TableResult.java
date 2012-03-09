package de.cinovo.q.query;

import de.cinovo.q.query.column.Column;

/**
 * Table result.
 *
 * @author mwittig
 *
 */
public interface TableResult extends Result {

	/**
	 * @return Number of rows
	 */
	int getRows();

	/**
	 * @return Number of columns
	 */
	int getCols();

	/**
	 * @param col Column
	 * @param row Row index
	 * @return Object or null
	 */
	Object getAt(final Column<?> col, final int row);

}
