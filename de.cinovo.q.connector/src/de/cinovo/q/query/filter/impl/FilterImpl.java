// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.filter.impl;

import de.cinovo.q.query.column.Column;
import de.cinovo.q.query.filter.Filter;
import de.cinovo.q.query.type.Type;

/**
 * Filter implementation.
 *
 * @author mwittig
 *
 * @param <T> Type
 */
public final class FilterImpl<T extends Type<?>> implements Filter {

	/** Column. */
	private final Column<T> column;

	/** Q. */
	private final String q;

	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final T right) {
		this.column = left;
		this.q = column.getName() + comparator.toQ() + right.toQ();
	}

	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final Column<T> right) {
		this.column = left;
		this.q = column.getName() + comparator.toQ() + right.toQ();
	}

	@Override
	public String toQ() {
		return this.q;
	}

	@Override
	public Column<T> getColumn() {
		return this.column;
	}

}
