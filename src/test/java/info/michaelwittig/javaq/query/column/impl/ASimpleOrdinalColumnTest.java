package info.michaelwittig.javaq.query.column.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * ASimpleOrdinalColumn test.
 * 
 * @author mwittig
 * 
 */
public class ASimpleOrdinalColumnTest {
	
	/** */
	@Test
	public final void test() {
		final IntegerColumn col = new IntegerColumn("test");
		Assert.assertEquals("test", col.toQ());
	}
	
}
