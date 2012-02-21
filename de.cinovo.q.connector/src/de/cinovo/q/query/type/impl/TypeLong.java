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
 * Long.
 *
 * @author mwittig
 *
 */
public final class TypeLong implements OrdinalType<Long> {

	/** Null. */
	public static final String NULL = "0Nj";

	/** Factory. */
	private static final TypeFactory<Long, TypeLong> FACTORY = new TypeFactory<Long, TypeLong>() {

		@Override
		public TypeLong create(final Long aValue) {
			return TypeLong.from(aValue);
		}
	};

	/**
	 * @param value Value
	 * @return Long
	 */
	public static TypeLong from(final Long value) {
		return new TypeLong(value);
	}

	/**
	 * @param values Values
	 * @return List of longs
	 */
	public static OrdinalList<Long, TypeLong> froms(final Long[] values) {
		return new OrdinalListImpl<Long, TypeLong>(values, FACTORY);
	}

	/** Value. */
	private final Long value;

	/**
	 * @param aValue Value
	 */
	private TypeLong(final Long aValue) {
		this.value = aValue;
	}

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return String.valueOf(this.value) + "j";
	}

}
