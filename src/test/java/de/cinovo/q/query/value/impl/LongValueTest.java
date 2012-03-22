// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * LongValue test.
 * 
 * @author mwittig
 * 
 */
public class LongValueTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Nj", LongValue.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1j", LongValue.from(-1L).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0j", LongValue.from(0L).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1j", LongValue.from(1L).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", LongValue.froms(new Long[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1j)", LongValue.froms(new Long[] { 1L }).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1j,2j)", LongValue.froms(new Long[] { 1L, 2L }).toQ());
	}

}
