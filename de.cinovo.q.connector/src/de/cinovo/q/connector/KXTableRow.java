// -------------------------------------------------------------------------------
// Copyright (c) 2011 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

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
