// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * RealValue test.
 *
 * @author mwittig
 *
 */
public class RealValueTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Ne", RealValue.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1e", RealValue.from(new BigDecimal("-1")).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0e", RealValue.from(BigDecimal.ZERO).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1e", RealValue.from(BigDecimal.ONE).toQ());
	}

	/** */
	@Test
	public final void testNeg15Q() {
		assertEquals("-1.5e", RealValue.from(new BigDecimal("-1.5")).toQ());
	}

	/** */
	@Test
	public final void test05Q() {
		assertEquals("0.5e", RealValue.from(new BigDecimal("0.5")).toQ());
	}

	/** */
	@Test
	public final void test15Q() {
		assertEquals("1.5e", RealValue.from(new BigDecimal("1.5")).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", RealValue.froms(new BigDecimal[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1.5e)", RealValue.froms(new BigDecimal[] {new BigDecimal("1.5")}).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1.5e,2.5e)", RealValue.froms(new BigDecimal[] {new BigDecimal("1.5"), new BigDecimal("2.5")}).toQ());
	}

}
