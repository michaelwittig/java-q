// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query;

import java.util.Collection;

import de.cinovo.q.Builder;
import de.cinovo.q.Q;
import de.cinovo.q.query.Select.SelectBuilder;
import de.cinovo.q.query.column.Column;

/**
 * Table.
 * 
 * @author mwittig
 * 
 */
public interface Table extends Q {
	
	/**
	 * @return Name
	 */
	String getName();
	
	/**
	 * @return Columns
	 */
	Collection<Column<?>> getColumns();
	
	/**
	 * @return SelectBuilder
	 */
	SelectBuilder select();
	
	/**
	 * @param result Result
	 * @return Readable result
	 */
	Iterable<TableRow> read(Result result);
	
	
	/**
	 * Table builder.
	 * 
	 * @author mwittig
	 * 
	 */
	public interface TableBuilder extends Builder<Table> {
		
		/**
		 * @param column Column
		 * @return TableBuilder
		 */
		TableBuilder column(final Column<?> column);
		
	}
	
}
