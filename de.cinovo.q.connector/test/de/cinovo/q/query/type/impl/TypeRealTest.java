package de.cinovo.q.query.type.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * TypeReal test.
 *
 * @author mwittig
 *
 */
public class TypeRealTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Ne", TypeReal.from(null).toQ());
	}

	/** */
	@Test
	public final void testNeg1Q() {
		assertEquals("-1e", TypeReal.from(new BigDecimal("-1")).toQ());
	}

	/** */
	@Test
	public final void test0Q() {
		assertEquals("0e", TypeReal.from(BigDecimal.ZERO).toQ());
	}

	/** */
	@Test
	public final void test1Q() {
		assertEquals("1e", TypeReal.from(BigDecimal.ONE).toQ());
	}

	/** */
	@Test
	public final void testNeg15Q() {
		assertEquals("-1.5e", TypeReal.from(new BigDecimal("-1.5")).toQ());
	}

	/** */
	@Test
	public final void test05Q() {
		assertEquals("0.5e", TypeReal.from(new BigDecimal("0.5")).toQ());
	}

	/** */
	@Test
	public final void test15Q() {
		assertEquals("1.5e", TypeReal.from(new BigDecimal("1.5")).toQ());
	}

}
