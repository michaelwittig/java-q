// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.impl;

import org.junit.Assert;
import org.junit.Test;

import de.cinovo.q.query.value.impl.SymbolValue;

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
