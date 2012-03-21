// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
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
		return INSTANCE;
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
