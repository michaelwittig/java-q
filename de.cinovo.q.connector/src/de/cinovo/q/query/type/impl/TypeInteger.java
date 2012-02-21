// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.OrdinalList;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.TypeFactory;


/**
 * Integer.
 *
 * @author mwittig
 *
 */
public final class TypeInteger implements OrdinalType<Integer> {

	/** Null. */
	public static final String NULL = "0N";

	/** Factory. */
	private static final TypeFactory<Integer, TypeInteger> FACTORY = new TypeFactory<Integer, TypeInteger>() {

		@Override
		public TypeInteger create(final Integer aValue) {
			return TypeInteger.from(aValue);
		}
	};

	/**
	 * @param value Value
	 * @return Integer
	 */
	public static TypeInteger from(final Integer value) {
		return new TypeInteger(value);
	}

	/**
	 * @param values Values
	 * @return List of integers
	 */
	public static OrdinalList<Integer, TypeInteger> froms(final Integer[] values) {
		return new OrdinalListImpl<Integer, TypeInteger>(values, FACTORY);
	}

	/** Value. */
	private final Integer value;

	/**
	 * @param aValue Value
	 */
	private TypeInteger(final Integer aValue) {
		this.value = aValue;
	}

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return String.valueOf(this.value);
	}

}
