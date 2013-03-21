// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import java.util.Date;

import de.cinovo.q.query.type.impl.TypeDateTime;

/**
 * DateTime column.
 * 
 * @author mwittig
 * 
 */
public class DateTimeColumn extends ASimpleOrdinalColumn<Date, TypeDateTime> {
	
	/**
	 * @param name Name
	 */
	public DateTimeColumn(final String name) {
		super(name, TypeDateTime.get());
	}
	
}
