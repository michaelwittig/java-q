// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.sql.Timestamp;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.TimestampValue;

/**
 * Timestamp.
 * 
 * @author mwittig
 * 
 */
public final class TypeTimestamp implements OrdinalType<Timestamp> {
	
	/** Instance. */
	private static final TypeTimestamp INSTANCE = new TypeTimestamp();
	
	
	/**
	 * @return Instance
	 */
	public static TypeTimestamp get() {
		return TypeTimestamp.INSTANCE;
	}
	
	@Override
	public ValueFactory<Timestamp, Type<Timestamp>> geValueFactory() {
		return new ValueFactory<Timestamp, Type<Timestamp>>() {
			
			@Override
			public Value<Timestamp, ? extends Type<Timestamp>> create(final Timestamp value) {
				return TimestampValue.from(value);
			}
			
			@Override
			public Value<Timestamp, ? extends Type<Timestamp>> fromQ(final Object value) {
				if (value == null) {
					return this.create(null);
				}
				if (value instanceof Timestamp) {
					return this.create((Timestamp) value);
				}
				throw new IllegalArgumentException("Type is " + value.getClass().getSimpleName());
			}
		};
	}
	
	/** */
	private TypeTimestamp() {
		super();
	}
	
}
