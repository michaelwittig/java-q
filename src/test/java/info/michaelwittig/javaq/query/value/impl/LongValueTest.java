package info.michaelwittig.javaq.query.value.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * LongValue test.
 * 
 * @author mwittig
 * 
 */
public class LongValueTest {
	
	/** */
	@Test
	public final void testNullQ() {
		Assert.assertEquals("0Nj", LongValue.from(null).toQ());
	}
	
	/** */
	@Test
	public final void testNeg1Q() {
		Assert.assertEquals("-1j", LongValue.from(-1L).toQ());
	}
	
	/** */
	@Test
	public final void test0Q() {
		Assert.assertEquals("0j", LongValue.from(0L).toQ());
	}
	
	/** */
	@Test
	public final void test1Q() {
		Assert.assertEquals("1j", LongValue.from(1L).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", LongValue.froms(new Long[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(1j)", LongValue.froms(new Long[] {1L}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(1j,2j)", LongValue.froms(new Long[] {1L, 2L}).toQ());
	}
	
}
