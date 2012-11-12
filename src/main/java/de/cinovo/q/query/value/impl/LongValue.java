// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.impl.TypeLong;

/**
 * Long value.
 * 
 * @author mwittig
 * 
 */
public final class LongValue extends AValue<Long, TypeLong> {
	
	/** Null. */
	public static final String NULL = "0Nj";
	
	
	/**
	 * @param value Value
	 * @return Long value
	 */
	public static LongValue from(final Long value) {
		return new LongValue(value, TypeLong.get());
	}
	
	/**
	 * @param values Values
	 * @return List of longs
	 */
	public static OrdinalList<Long, TypeLong> froms(final Long[] values) {
		return new OrdinalListImpl<Long, TypeLong>(values, TypeLong.get());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	public LongValue(final Long value, final TypeLong type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return LongValue.NULL;
		}
		return String.valueOf(this.get()) + "j";
	}
	
}
