// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

import java.sql.Timestamp;
import java.util.List;

/**
 * KXTable.
 * 
 * @author mwittig
 * 
 * @deprecated Because we have the even better Result
 */
@Deprecated
public interface KXTable extends Iterable<KXTableRow> {
	
	/**
	 * @param row Row
	 * @return KXDataRow
	 */
	KXTableRow getRow(final int row);
	
	/**
	 * @param col Column
	 * @param row Row
	 * @return Cell data
	 */
	Object getAt(final int col, final int row);
	
	/**
	 * @param col Column
	 * @param row Row
	 * @return Cell data
	 */
	Object getAt(final String col, final int row);
	
	/**
	 * @param col Column
	 * @param row Row
	 * @return Cell data
	 */
	String getStringAt(final String col, final int row);
	
	/**
	 * @param col Column
	 * @param row Row
	 * @return Cell data
	 */
	float getFloatAt(final String col, final int row);
	
	/**
	 * @param col Column
	 * @param row Row
	 * @return Cell data
	 */
	Timestamp getTimestampAt(final String col, final int row);
	
	/**
	 * @return Number of columns
	 */
	int getCols();
	
	/**
	 * @return Name of columns
	 */
	List<String> getColNames();
	
	/**
	 * @return Number of rows
	 */
	int getRows();
	
	/**
	 * @return Table name
	 */
	String getTable();
	
	/**
	 * @return List with rows
	 */
	List<KXTableRow> toList();
	
}
