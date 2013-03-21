// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column.impl;

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
