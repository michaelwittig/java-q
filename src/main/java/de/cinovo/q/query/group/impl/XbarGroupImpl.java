// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.group.impl;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.group.Group;
import de.cinovo.q.query.type.OrdinalType;
import de.cinovo.q.query.value.Value;

/**
 * Xbar group implementation.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 * @param <T> Type
 */
public final class XbarGroupImpl<J, T extends OrdinalType<J>> implements Group {
	
	/** Xbar. */
	private final Value<J, T> xbar;
	
	/** Column. */
	private final Column<T> column;
	
	
	/**
	 * @param aXbar Xbar
	 * @param aColumn Column
	 */
	public XbarGroupImpl(final Value<J, T> aXbar, final Column<T> aColumn) {
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
