// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.impl.TypeList;
import de.cinovo.q.query.type.impl.TypeReal;

/**
 * Real value.
 * 
 * @author mwittig
 * 
 */
public final class RealValue extends AValue<BigDecimal, TypeReal> {
	
	/** Null. */
	public static final String NULL = "0Ne";
	
	
	/**
	 * @param value Value
	 * @return Real
	 */
	public static RealValue from(final BigDecimal value) {
		return new RealValue(value, TypeReal.get());
	}
	
	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>> froms(final BigDecimal[] values) {
		return new ListValue<BigDecimal, TypeList<BigDecimal, Type<BigDecimal>>>(values, TypeList.getReal());
	}
	
	/**
	 * @param value Value
	 * @param type Type
	 */
	private RealValue(final BigDecimal value, final TypeReal type) {
		super(value, type);
	}
	
	@Override
	public String toQ() {
		if (this.get() == null) {
			return RealValue.NULL;
		}
		return this.get() + "e";
	}
	
}
