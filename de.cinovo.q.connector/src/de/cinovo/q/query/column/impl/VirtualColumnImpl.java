// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.column.VirtualColumn;
import de.cinovo.q.query.type.Type;

/**
 * Virtual column implementation.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public final class VirtualColumnImpl<T extends Type<?>> implements VirtualColumn<T> {

	/** Virtual. */
	private final String virtual;

	/** Type. */
	private final Column<T> column;

	/**
	 * @param aVirtual Virtual
	 * @param aColumn Column
	 */
	protected VirtualColumnImpl(final String aVirtual, final Column<T> aColumn) {
		this.virtual = aVirtual;
		this.column = aColumn;
	}

	@Override
	public String toQ() {
		return this.virtual + ":" + this.column.toQ();
	}

	@Override
	public String getName() {
		return this.column.getName();
	}

	@Override
	public Class<T> getType() {
		return this.column.getType();
	}

	@Override
	public String getVirtual() {
		return this.virtual;
	}

}
