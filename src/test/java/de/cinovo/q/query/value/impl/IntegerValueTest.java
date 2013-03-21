// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import org.junit.Assert;
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
		Assert.assertEquals("0N", IntegerValue.from(null).toQ());
	}
	
	/** */
	@Test
	public final void testNeg1Q() {
		Assert.assertEquals("-1", IntegerValue.from(-1).toQ());
	}
	
	/** */
	@Test
	public final void test0Q() {
		Assert.assertEquals("0", IntegerValue.from(0).toQ());
	}
	
	/** */
	@Test
	public final void test1Q() {
		Assert.assertEquals("1", IntegerValue.from(1).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", IntegerValue.froms(new Integer[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(1)", IntegerValue.froms(new Integer[] {1}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(1,2)", IntegerValue.froms(new Integer[] {1, 2}).toQ());
	}
	
}
