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
 * Integer.
 *
 * @author mwittig
 *
 */
public final class TypeInteger implements OrdinalType<Integer> {

	/** Null. */
	public static final String NULL = "0N";

	/**
	 * @param value Value
	 * @return Integer
	 */
	public static TypeInteger from(final Integer value) {
		return new TypeInteger(value);
	}

	/** Value. */
	private final Integer value;

	@Override
	public String toQ() {
		if (this.value == null) {
			return NULL;
		}
		return String.valueOf(this.value);
	}

	/**
	 * @param aValue Value
	 */
	private TypeInteger(final Integer aValue) {
		this.value = aValue;
	}

}
