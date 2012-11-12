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
import de.cinovo.q.query.value.impl.BooleanValue;

/**
 * Boolean.
 * 
 * @author mwittig
 * 
 */
public final class TypeBoolean implements OrdinalType<Boolean> {
	
	/** Instance. */
	private static final TypeBoolean INSTANCE = new TypeBoolean();
	
	
	/**
	 * @return Instance
	 */
	public static TypeBoolean get() {
		return TypeBoolean.INSTANCE;
	}
	
	@Override
	public ValueFactory<Boolean, Type<Boolean>> geValueFactory() {
		return new ValueFactory<Boolean, Type<Boolean>>() {
			
			@Override
			public Value<Boolean, ? extends Type<Boolean>> create(final Boolean value) {
				return BooleanValue.from(value);
			}
			
			@Override
			public Value<Boolean, ? extends Type<Boolean>> fromQ(final Object value) {
				if (value instanceof Boolean) {
					return this.create((Boolean) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeBoolean() {
		super();
	}
	
}
