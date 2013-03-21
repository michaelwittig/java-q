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
 * Boolean value test.
 * 
 * @author mwittig
 * 
 */
public class BooleanValueTest {
	
	/**
	 * Null.
	 */
	@Test
	public void testNullQ() {
		Assert.assertEquals("", BooleanValue.from(null).toQ());
	}
	
	/**
	 * True.
	 */
	@Test
	public void testTrueQ() {
		Assert.assertEquals("1b", BooleanValue.from(true).toQ());
	}
	
	/**
	 * False.
	 */
	@Test
	public void testFalseQ() {
		Assert.assertEquals("0b", BooleanValue.from(false).toQ());
	}
	
	/** */
	@Test
	public final void testListNull() {
		Assert.assertEquals("()", BooleanValue.froms(null).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", BooleanValue.froms(new Boolean[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(1b)", BooleanValue.froms(new Boolean[] {true}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(1b,0b)", BooleanValue.froms(new Boolean[] {true, false}).toQ());
	}
	
}
