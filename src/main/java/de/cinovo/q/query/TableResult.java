// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

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
	
	/**
	 * @param colName Column name
	 * @param row Row index
	 * @return Object or null
	 */
	Object getAt(final String colName, final int row);
	
	/**
	 * @param colIndex Column index
	 * @param row Row index
	 * @return Object or null
	 */
	Object getAt(final int colIndex, final int row);
	
	/**
	 * @return List of all column names
	 */
	String[] getColNames();
	
}
