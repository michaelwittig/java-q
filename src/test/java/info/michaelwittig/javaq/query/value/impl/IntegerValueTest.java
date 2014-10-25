package info.michaelwittig.javaq.query.value.impl;

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
