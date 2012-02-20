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
 * TypeInteger test.
 *
 * @author mwittig
 *
 */
public class TypeIntegerTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0N", TypeInteger.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1", TypeInteger.from(-1).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0", TypeInteger.from(0).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1", TypeInteger.from(1).toQ());
	}

}
