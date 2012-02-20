// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import de.cinovo.q.query.type.OrdinalType;


/**
 * Long.
 *
 * @author mwittig
 *
 */
public final class TypeLong implements OrdinalType<Long> {

	/** Null. */
	public static final String NULL = "0Nj";

	/**
	 * @param value Value
	 * @return Integer
	 */
	public static TypeLong from(final Long value) {
		return new TypeLong(value);
	}

	/** Value. */
	private final Long value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return String.valueOf(this.value) + "j";
	}

	/**
	 * @param aValue Value
	 */
	private TypeLong(final Long aValue) {
		this.value = aValue;
	}

}
