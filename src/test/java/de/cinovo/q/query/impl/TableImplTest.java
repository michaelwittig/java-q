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

import de.cinovo.q.query.Table;
import de.cinovo.q.query.column.impl.SymbolColumn;
import de.cinovo.q.query.column.impl.TimeColumn;
import de.cinovo.q.query.impl.TableImpl.TableBuilderImpl;

/**
 * TableImpl test.
 * 
 * @author mwittig
 * 
 */
public class TableImplTest {
	
	/** */
	@Test
	public final void test() {
		final TimeColumn utctime = new TimeColumn("utctime");
		final SymbolColumn sym = new SymbolColumn("sym");
		final Table table = new TableBuilderImpl("test").column(utctime).column(sym).build();
		Assert.assertEquals("test", table.toQ());
	}
	
}
