// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.type.ValueFactory;
import de.cinovo.q.query.value.Value;
import de.cinovo.q.query.value.impl.RealValue;


/**
 * Real.
 *
 * @author mwittig
 *
 */
public final class TypeReal implements OrdinalType<BigDecimal> {

	/** Instance. */
	private static final TypeReal INSTANCE = new TypeReal();

	/**
	 * @return Instance
	 */
	public static TypeReal get() {
		return INSTANCE;
	}

	@Override
	public ValueFactory<BigDecimal, Type<BigDecimal>> geValueFactory() {
		return new ValueFactory<BigDecimal, Type<BigDecimal>>() {

			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> create(final BigDecimal aValue) {
				return  new RealValue(aValue, TypeReal.this);
			}

			@Override
			public Value<BigDecimal, ? extends Type<BigDecimal>> fromQ(final Object aValue) {
				if (aValue instanceof Double) {
					return this.create(new BigDecimal((Double) aValue));
				}
				throw new IllegalArgumentException("Type is " + aValue.getClass().getSimpleName());
			}
		};
	}

	/**  */
	private TypeReal() {
		super();
	}

}
