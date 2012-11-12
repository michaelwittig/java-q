// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
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
import de.cinovo.q.query.value.impl.LongValue;

/**
 * Long.
 * 
 * @author mwittig
 * 
 */
public final class TypeLong implements OrdinalType<Long> {
	
	/** Instance. */
	private static final TypeLong INSTANCE = new TypeLong();
	
	
	/**
	 * @return Instance
	 */
	public static TypeLong get() {
		return TypeLong.INSTANCE;
	}
	
	@Override
	public ValueFactory<Long, Type<Long>> geValueFactory() {
		return new ValueFactory<Long, Type<Long>>() {
			
			@Override
			public Value<Long, ? extends Type<Long>> create(final Long value) {
				return LongValue.from(value);
			}
			
			@Override
			public Value<Long, ? extends Type<Long>> fromQ(final Object value) {
				if (value instanceof Long) {
					return this.create((Long) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
			
		};
	}
	
	/** */
	private TypeLong() {
		super();
	}
	
}
