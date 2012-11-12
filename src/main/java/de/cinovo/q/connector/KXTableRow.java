// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
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
	Object getBy(int col);
	
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
