// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.IntegerValue;

/**
 * Integer.
 * 
 * @author mwittig
 * 
 */
public final class TypeInteger implements OrdinalType<Integer> {
	
	/** Instance. */
	private static final TypeInteger INSTANCE = new TypeInteger();
	
	
	/**
	 * @return Instance
	 */
	public static TypeInteger get() {
		return TypeInteger.INSTANCE;
	}
	
	@Override
	public ValueFactory<Integer, Type<Integer>> geValueFactory() {
		return new ValueFactory<Integer, Type<Integer>>() {
			
			@Override
			public Value<Integer, ? extends Type<Integer>> create(final Integer value) {
				return IntegerValue.from(value);
			}
			
			@Override
			public Value<Integer, ? extends Type<Integer>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Integer) {
					return this.create((Integer) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeInteger() {
		super();
	}
	
}
