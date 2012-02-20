// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * TypeSymbol test.
 *
 * @author mwittig
 *
 */
public class TypeSymbolTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("`", TypeSymbol.from(null).toQ());
	}

	/** */
	@Test
	public final void testQ() {
		assertEquals("`FGBL032012", TypeSymbol.from("FGBL032012").toQ());
	}

}
