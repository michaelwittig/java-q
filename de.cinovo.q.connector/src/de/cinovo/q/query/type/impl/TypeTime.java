// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
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
		return INSTANCE;
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
