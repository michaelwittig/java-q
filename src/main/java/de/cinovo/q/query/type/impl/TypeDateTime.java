// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.util.Date;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.DateTimeValue;

/**
 * DateTime.
 * 
 * @author mwittig
 * 
 */
public final class TypeDateTime implements OrdinalType<Date> {
	
	/** Instance. */
	private static final TypeDateTime INSTANCE = new TypeDateTime();
	
	
	/**
	 * @return Instance
	 */
	public static TypeDateTime get() {
		return TypeDateTime.INSTANCE;
	}
	
	@Override
	public ValueFactory<Date, Type<Date>> geValueFactory() {
		return new ValueFactory<Date, Type<Date>>() {
			
			@Override
			public Value<Date, ? extends Type<Date>> create(final Date value) {
				return DateTimeValue.from(value);
			}
			
			@Override
			public Value<Date, ? extends Type<Date>> fromQ(final Object aValue) {
				if (aValue instanceof Date) {
					return this.create((Date) aValue);
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
			
		};
	}
	
	/**  */
	private TypeDateTime() {
		super();
	}
	
}
