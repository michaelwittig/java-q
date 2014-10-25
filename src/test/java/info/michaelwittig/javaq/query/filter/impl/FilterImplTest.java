package info.michaelwittig.javaq.query.filter.impl;

import info.michaelwittig.javaq.query.column.impl.IntegerColumn;
import info.michaelwittig.javaq.query.type.impl.TypeInteger;
import info.michaelwittig.javaq.query.value.impl.IntegerValue;

import org.junit.Assert;
import org.junit.Test;

/**
 * FilterImpl test.
 * 
 * @author mwittig
 * 
 */
public class FilterImplTest {
	
	/** */
	@Test
	public final void testColumnEqualsColumn() {
		final IntegerColumn c1 = new IntegerColumn("left");
		final IntegerColumn c2 = new IntegerColumn("right");
		final FilterImpl<Integer, TypeInteger> filter = new FilterImpl<Integer, TypeInteger>(c1, FilterComparator.equal, c2);
		Assert.assertEquals("left=right", filter.toQ());
	}
	
	/** */
	@Test
	public final void testColumnEqualsValue() {
		final IntegerColumn c1 = new IntegerColumn("left");
		final FilterImpl<Integer, TypeInteger> filter = new FilterImpl<Integer, TypeInteger>(c1, FilterComparator.equal, IntegerValue.from(10));
		Assert.assertEquals("left=10", filter.toQ());
	}
	
}
