package de.cinovo.q.connector;

import java.sql.Timestamp;

/**
 * KXTableRow.
 *
 * @author mwittig
 *
 */
public interface KXTableRow {

	/**
	 * @param col Column
	 * @return Cell data
	 */
	Object get(int col);

	/**
	 * @param col Column
	 * @return Cell data
	 */
	Object get(String col);

	/**
	 * @param col Column
	 * @return Cell data
	 */
	String getString(String col);

	/**
	 * @param col Column
	 * @return Cell data
	 */
	float getFloat(String col);

	/**
	 * @param col Column
	 * @return Cell data
	 */
	Timestamp getTimestamp(String col);

}
