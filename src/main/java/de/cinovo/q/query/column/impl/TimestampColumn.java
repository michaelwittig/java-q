// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import java.sql.Timestamp;

import de.cinovo.q.query.type.impl.TypeTimestamp;

/**
 * Timestamp column.
 * 
 * @author mwittig
 * 
 */
public class TimestampColumn extends ASimpleOrdinalColumn<Timestamp, TypeTimestamp> {
	
	/**
	 * @param name Name
	 */
	public TimestampColumn(final String name) {
		super(name, TypeTimestamp.get());
	}
	
}
