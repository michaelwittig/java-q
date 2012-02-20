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

}
