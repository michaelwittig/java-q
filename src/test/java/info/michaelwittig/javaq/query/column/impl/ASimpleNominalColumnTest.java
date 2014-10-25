package info.michaelwittig.javaq.query.column.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * ASimpleNominalColumn test.
 * 
 * @author mwittig
 * 
 */
public class ASimpleNominalColumnTest {
	
	/** */
	@Test
	public final void test() {
		final SymbolColumn col = new SymbolColumn("test");
		Assert.assertEquals("test", col.toQ());
	}
	
}
