// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import java.math.BigDecimal;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.TypeFactory;


/**
 * Real.
 *
 * @author mwittig
 *
 */
public final class TypeReal implements OrdinalType<BigDecimal> {

	/** Null. */
	public static final String NULL = "0Ne";

	/** Factory. */
	private static final TypeFactory<BigDecimal, TypeReal> FACTORY = new TypeFactory<BigDecimal, TypeReal>() {

		@Override
		public TypeReal create(final BigDecimal aValue) {
			return TypeReal.from(aValue);
		}
	};

	/**
	 * @param value Value
	 * @return Real
	 */
	public static TypeReal from(final BigDecimal value) {
		return new TypeReal(value);
	}

	/**
	 * @param values Values
	 * @return List of reals
	 */
	public static OrdinalList<BigDecimal, TypeReal> froms(final BigDecimal[] values) {
		return new OrdinalListImpl<BigDecimal, TypeReal>(values, FACTORY);
	}

	/** Value. */
	private final BigDecimal value;

	/**
	 * @param aValue Value
	 */
	private TypeReal(final BigDecimal aValue) {
		this.value = aValue;
	}

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return this.value + "e";
	}

}
