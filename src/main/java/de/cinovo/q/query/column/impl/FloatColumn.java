// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.impl.TypeFloat;

/**
 * Float column.
 * 
 * @author mwittig
 * 
 */
public class FloatColumn extends ASimpleOrdinalColumn<BigDecimal, TypeFloat> {
	
	/**
	 * @param name Name
	 */
	public FloatColumn(final String name) {
		super(name, TypeFloat.get());
	}
	
}
