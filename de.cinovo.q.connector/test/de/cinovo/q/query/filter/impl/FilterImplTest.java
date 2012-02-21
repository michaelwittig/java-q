// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.filter.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.cinovo.q.query.column.impl.IntegerColumn;
import de.cinovo.q.query.type.impl.TypeInteger;

/**
 * FilterImpl test.
 *
 * @author mwittig
 *
 */
public class FilterImplTest {

	/** */
	@Test
	public final void testColumnEqualsColumn() {
		final IntegerColumn c1 = new IntegerColumn("left");
		final IntegerColumn c2 = new IntegerColumn("right");
		final FilterImpl<Integer, TypeInteger> filter = new FilterImpl<Integer, TypeInteger>(c1, FilterComparator.equal, c2);
		assertEquals("left=right", filter.toQ());
	}

	/** */
	@Test
	public final void testColumnEqualsValue() {
		final IntegerColumn c1 = new IntegerColumn("left");
		final FilterImpl<Integer, TypeInteger> filter = new FilterImpl<Integer, TypeInteger>(c1, FilterComparator.equal, TypeInteger.from(10));
		assertEquals("left=10", filter.toQ());
	}

}
