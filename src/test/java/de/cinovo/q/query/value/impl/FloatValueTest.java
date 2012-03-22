// -------------------------------------------------------------------------------
// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * FloatValue test.
 * 
 * @author mwittig
 * 
 */
public class FloatValueTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0n", FloatValue.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1f", FloatValue.from(new BigDecimal("-1")).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0f", FloatValue.from(BigDecimal.ZERO).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1f", FloatValue.from(BigDecimal.ONE).toQ());
	}

	/** */
	@Test
	public final void testNeg15Q() {
		assertEquals("-1.5f", FloatValue.from(new BigDecimal("-1.5")).toQ());
	}

	/** */
	@Test
	public final void test05Q() {
		assertEquals("0.5f", FloatValue.from(new BigDecimal("0.5")).toQ());
	}

	/** */
	@Test
	public final void test15Q() {
		assertEquals("1.5f", FloatValue.from(new BigDecimal("1.5")).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", FloatValue.froms(new BigDecimal[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1.5f)", FloatValue.froms(new BigDecimal[] { new BigDecimal("1.5") }).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1.5f,2.5f)", FloatValue.froms(new BigDecimal[] { new BigDecimal("1.5"), new BigDecimal("2.5") }).toQ());
	}

}
