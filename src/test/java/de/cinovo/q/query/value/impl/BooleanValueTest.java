// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class BooleanValueTest {

	@Test
	public void testNullQ() {
		assertEquals("", BooleanValue.from(null).toQ());
	}
	
	@Test
	public void testTrueQ() {
		assertEquals("1b", BooleanValue.from(true).toQ());
	}
	
	@Test
	public void testFalseQ() {
		assertEquals("0b", BooleanValue.from(false).toQ());
	}
	
	/** */
	@Test
	public final void testListNull() {
		assertEquals("()", BooleanValue.froms(null).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", BooleanValue.froms(new Boolean[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1b)", BooleanValue.froms(new Boolean[] { true }).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1b,0b)", BooleanValue.froms(new Boolean[] { true, false }).toQ());
	}

}
