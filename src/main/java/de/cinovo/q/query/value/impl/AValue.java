// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.value.Value;

/**
 * Abstract value.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
abstract class AValue<J, T extends Type<J>> implements Value<J, T> {
	
	/** Value. */
	private final J value;
	
	/** Type. */
	private final T type;
	
	
	/**
	 * @param aValue Value
	 * @param aType Type
	 */
	public AValue(final J aValue, final T aType) {
		super();
		this.value = aValue;
		this.type = aType;
	}
	
	@Override
	public final J get() {
		return this.value;
	}
	
	@Override
	public final T getType() {
		return this.type;
	}
	
}
