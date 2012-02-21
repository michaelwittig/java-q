// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * TypeLong test.
 *
 * @author mwittig
 *
 */
public class TypeLongTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Nj", TypeLong.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1j", TypeLong.from(-1L).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0j", TypeLong.from(0L).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1j", TypeLong.from(1L).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", TypeLong.froms(new Long[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1j)", TypeLong.froms(new Long[] {1L}).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1j,2j)", TypeLong.froms(new Long[] {1L, 2L}).toQ());
	}

}
