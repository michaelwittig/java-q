// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.filter.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.cinovo.q.query.column.impl.IntegerColumn;
import de.cinovo.q.query.type.impl.TypeInteger;
import de.cinovo.q.query.value.impl.IntegerValue;

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
		final FilterImpl<Integer, TypeInteger> filter = new FilterImpl<Integer, TypeInteger>(c1, FilterComparator.equal, IntegerValue.from(10));
		assertEquals("left=10", filter.toQ());
	}

}
