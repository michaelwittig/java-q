package info.michaelwittig.javaq.query.impl;

import info.michaelwittig.javaq.query.value.impl.SymbolValue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Function implementation tests.
 * 
 * @author mwittig
 * 
 */
public class FunctionImplTest {
	
	/**
	 * Test with no arguments.
	 */
	@Test
	public void testNoArg() {
		Assert.assertEquals("test[]", new FunctionImpl.FunctionBuilderImpl("test").build().toQ());
	}
	
	/**
	 * Test with a list argument.
	 */
	@Test
	public void testListArg() {
		Assert.assertEquals("test[(`a,`b,`c)]", new FunctionImpl.FunctionBuilderImpl("test").param(SymbolValue.froms("a", "b", "c")).build().toQ());
	}
	
}
