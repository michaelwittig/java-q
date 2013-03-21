// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

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
