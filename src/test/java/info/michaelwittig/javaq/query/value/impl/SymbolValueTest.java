package info.michaelwittig.javaq.query.value.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * SymbolValue test.
 * 
 * @author mwittig
 * 
 */
public class SymbolValueTest {
	
	/** */
	@Test
	public final void testNullQ() {
		Assert.assertEquals("`", SymbolValue.from(null).toQ());
	}
	
	/** */
	@Test
	public final void testQ() {
		Assert.assertEquals("`FGBL032012", SymbolValue.from("FGBL032012").toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", SymbolValue.froms(new String[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(`FGBL032012)", SymbolValue.froms(new String[] {"FGBL032012"}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(`FGBL032012,`FGBM032012)", SymbolValue.froms(new String[] {"FGBL032012", "FGBM032012"}).toQ());
	}
	
}
