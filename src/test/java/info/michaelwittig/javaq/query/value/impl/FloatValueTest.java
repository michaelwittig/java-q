package info.michaelwittig.javaq.query.value.impl;

import java.math.BigDecimal;

import org.junit.Assert;
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
		Assert.assertEquals("0n", FloatValue.from(null).toQ());
	}
	
	/** */
	@Test
	public final void testNeg1Q() {
		Assert.assertEquals("-1f", FloatValue.from(new BigDecimal("-1")).toQ());
	}
	
	/** */
	@Test
	public final void test0Q() {
		Assert.assertEquals("0f", FloatValue.from(BigDecimal.ZERO).toQ());
	}
	
	/** */
	@Test
	public final void test1Q() {
		Assert.assertEquals("1f", FloatValue.from(BigDecimal.ONE).toQ());
	}
	
	/** */
	@Test
	public final void testNeg15Q() {
		Assert.assertEquals("-1.5f", FloatValue.from(new BigDecimal("-1.5")).toQ());
	}
	
	/** */
	@Test
	public final void test05Q() {
		Assert.assertEquals("0.5f", FloatValue.from(new BigDecimal("0.5")).toQ());
	}
	
	/** */
	@Test
	public final void test15Q() {
		Assert.assertEquals("1.5f", FloatValue.from(new BigDecimal("1.5")).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", FloatValue.froms(new BigDecimal[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(1.5f)", FloatValue.froms(new BigDecimal[] {new BigDecimal("1.5")}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(1.5f,2.5f)", FloatValue.froms(new BigDecimal[] {new BigDecimal("1.5"), new BigDecimal("2.5")}).toQ());
	}
	
}
