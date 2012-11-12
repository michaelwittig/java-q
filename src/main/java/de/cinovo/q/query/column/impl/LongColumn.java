// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.type.impl.TypeLong;

/**
 * Long column.
 * 
 * @author mwittig
 * 
 */
public class LongColumn extends ASimpleOrdinalColumn<Long, TypeLong> {
	
	/**
	 * @param name Name
	 */
	public LongColumn(final String name) {
		super(name, TypeLong.get());
	}
	
}
