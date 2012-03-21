// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * IntegerValue test.
 *
 * @author mwittig
 *
 */
public class IntegerValueTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0N", IntegerValue.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1", IntegerValue.from(-1).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0", IntegerValue.from(0).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1", IntegerValue.from(1).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", IntegerValue.froms(new Integer[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1)", IntegerValue.froms(new Integer[] {1}).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1,2)", IntegerValue.froms(new Integer[] {1, 2}).toQ());
	}

}
