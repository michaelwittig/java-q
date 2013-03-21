// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeBoolean;
import de.cinovo.q.query.type.impl.TypeList;

/**
 * Boolean value.
 * 
 * @author mwittig
 * 
 */
public final class BooleanValue extends AValue<Boolean, TypeBoolean> {
	
	/** Null. */
	public static final String NULL = "";
	
	
	/**
	 * @param value Value
	 * @return Real
	 */
	public static BooleanValue from(final Boolean value) {
		return new BooleanValue(value, TypeBoolean.get());
	}
	
	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static ListValue<Boolean, TypeList<Boolean, Type<Boolean>>> froms(final Boolean[] values) {
		return new ListValue<Boolean, TypeList<Boolean, Type<Boolean>>>(values, TypeList.getBoolean());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private BooleanValue(final Boolean value, final TypeBoolean type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return BooleanValue.NULL;
		}
		if (this.get() == true) {
			return "1b";
		}
		return "0b";
	}
	
}
