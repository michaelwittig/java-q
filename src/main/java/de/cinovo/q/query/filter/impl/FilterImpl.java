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
import de.cinovo.q.query.type.List;
import de.cinovo.q.query.type.Type;
import de.cinovo.q.query.value.Value;

/**
 * Filter implementation.
 *
 * @author mwittig
 *
 * @param <J> Java type
 * @param <T> Type
 */
public final class FilterImpl<J, T extends Type<J>> implements Filter {

	/** Column. */
	private final Column<T> column;

	/** Q. */
	private final String q;

	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final Value<J, T> right) {
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

	/**
	 * @param left Left
	 * @param comparator Comparator
	 * @param right Right
	 */
	public FilterImpl(final Column<T> left, final FilterComparator comparator, final List<J, T> right) {
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
