// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.impl;

import static org.junit.Assert.assertEquals;

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
		final Table table = new TableBuilderImpl("test")
			.column(utctime)
			.column(sym)
			.build();
		assertEquals("test", table.toQ());
	}

}
