// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.sql.Date;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.DateValue;

/**
 * Date.
 * 
 * @author mwittig
 * 
 */
public final class TypeDate implements OrdinalType<Date> {
	
	/** Instance. */
	private static final TypeDate INSTANCE = new TypeDate();
	
	
	/**
	 * @return Instance
	 */
	public static TypeDate get() {
		return TypeDate.INSTANCE;
	}
	
	@Override
	public ValueFactory<Date, Type<Date>> geValueFactory() {
		return new ValueFactory<Date, Type<Date>>() {
			
			@Override
			public Value<Date, ? extends Type<Date>> create(final Date value) {
				return DateValue.from(value);
			}
			
			@Override
			public Value<Date, ? extends Type<Date>> fromQ(final Object aValue) {
				if (aValue == null) {
					return this.create(null);
				}
				if (aValue instanceof Date) {
					return this.create((Date) aValue);
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
			
		};
	}
	
	/**  */
	private TypeDate() {
		super();
	}
	
}
