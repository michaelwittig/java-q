// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.FloatValue;

/**
 * Float.
 * 
 * @author mwittig
 * 
 */
public final class TypeFloat implements OrdinalType<BigDecimal> {
	
	/** Instance. */
	private static final TypeFloat INSTANCE = new TypeFloat();
	
	
	/**
	 * @return Instance
	 */
	public static TypeFloat get() {
		return TypeFloat.INSTANCE;
	}
	
	@Override
	public ValueFactory<BigDecimal, Type<BigDecimal>> geValueFactory() {
		return new ValueFactory<BigDecimal, Type<BigDecimal>>() {
			
			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> create(final BigDecimal value) {
				return FloatValue.from(value);
			}
			
			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Float) {
					return this.create(new BigDecimal((Float) value));
				}
				if (value instanceof Double) {
					return this.create(new BigDecimal((Double) value));
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeFloat() {
		super();
	}
	
}
