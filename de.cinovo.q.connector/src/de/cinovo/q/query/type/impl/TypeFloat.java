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
 * Float.
 *
 * @author mwittig
 *
 */
public final class TypeFloat implements OrdinalType<BigDecimal> {

	/** Null. */
	public static final String NULL = "0n";

	/** Factory. */
	private static final TypeFactory<BigDecimal, TypeFloat> FACTORY = new TypeFactory<BigDecimal, TypeFloat>() {

		@Override
		public TypeFloat create(final BigDecimal aValue) {
			return TypeFloat.from(aValue);
		}
	};

	/**
	 * @param value Value
	 * @return Float
	 */
	public static TypeFloat from(final BigDecimal value) {
		return new TypeFloat(value);
	}

	/**
	 * @param values Values
	 * @return List of floats
	 */
	public static OrdinalList<BigDecimal, TypeFloat> froms(final BigDecimal[] values) {
		return new OrdinalListImpl<BigDecimal, TypeFloat>(values, FACTORY);
	}

	/** Value. */
	private final BigDecimal value;
	/**
	 * @param aValue Value
	 */
	private TypeFloat(final BigDecimal aValue) {
		this.value = aValue;
	}

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return this.value + "f";
	}

}
