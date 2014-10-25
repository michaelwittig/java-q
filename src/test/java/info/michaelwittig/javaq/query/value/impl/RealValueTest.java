package info.michaelwittig.javaq.query.value.impl;

import java.math.BigDecimal;

import org.junit.Assert;
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
		Assert.assertEquals("0Ne", RealValue.from(null).toQ());
	}
	
	/** */
	@Test
	public final void testNeg1Q() {
		Assert.assertEquals("-1e", RealValue.from(new BigDecimal("-1")).toQ());
	}
	
	/** */
	@Test
	public final void test0Q() {
		Assert.assertEquals("0e", RealValue.from(BigDecimal.ZERO).toQ());
	}
	
	/** */
	@Test
	public final void test1Q() {
		Assert.assertEquals("1e", RealValue.from(BigDecimal.ONE).toQ());
	}
	
	/** */
	@Test
	public final void testNeg15Q() {
		Assert.assertEquals("-1.5e", RealValue.from(new BigDecimal("-1.5")).toQ());
	}
	
	/** */
	@Test
	public final void test05Q() {
		Assert.assertEquals("0.5e", RealValue.from(new BigDecimal("0.5")).toQ());
	}
	
	/** */
	@Test
	public final void test15Q() {
		Assert.assertEquals("1.5e", RealValue.from(new BigDecimal("1.5")).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", RealValue.froms(new BigDecimal[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(1.5e)", RealValue.froms(new BigDecimal[] {new BigDecimal("1.5")}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(1.5e,2.5e)", RealValue.froms(new BigDecimal[] {new BigDecimal("1.5"), new BigDecimal("2.5")}).toQ());
	}
	
}
