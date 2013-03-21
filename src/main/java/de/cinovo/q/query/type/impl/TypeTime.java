// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.sql.Time;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.TimeValue;

/**
 * Time.
 * 
 * @author mwittig
 * 
 */
public final class TypeTime implements OrdinalType<Time> {
	
	/** Instance. */
	private static final TypeTime INSTANCE = new TypeTime();
	
	
	/**
	 * @return Instance
	 */
	public static TypeTime get() {
		return TypeTime.INSTANCE;
	}
	
	@Override
	public ValueFactory<Time, Type<Time>> geValueFactory() {
		return new ValueFactory<Time, Type<Time>>() {
			
			@Override
			public Value<Time, ? extends Type<Time>> create(final Time value) {
				return TimeValue.from(value);
			}
			
			@Override
			public Value<Time, ? extends Type<Time>> fromQ(final Object aValue) {
				if (aValue == null) {
					return this.create(null);
				}
				if (aValue instanceof Time) {
					return this.create((Time) aValue);
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
			
		};
	}
	
	/**  */
	private TypeTime() {
		super();
	}
	
}
