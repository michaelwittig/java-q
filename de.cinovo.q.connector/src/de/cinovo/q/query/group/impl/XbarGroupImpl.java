// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.group.impl;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.group.Group;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.type.impl.TypeInteger;

/**
 * Xbar group implementation.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public final class XbarGroupImpl<T extends OrdinalType<?>> implements Group {

	/** Xbar. */
	private final TypeInteger xbar;

	/** Column. */
	private final Column<T> column;

	/**
	 * @param aXbar Xbar
	 * @param aColumn Column
	 */
	public XbarGroupImpl(final TypeInteger aXbar, final Column<T> aColumn) {
		super();
		this.column = aColumn;
		this.xbar = aXbar;
	}


	@Override
	public String toQ() {
		return this.xbar.toQ() + " xbar " + this.column.toQ();
	}


	@Override
	public Column<?> getColumn() {
		return this.column;
	}

}
