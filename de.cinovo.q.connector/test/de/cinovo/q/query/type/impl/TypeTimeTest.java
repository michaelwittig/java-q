// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Time;

import org.junit.Test;

/**
 * TypeTime test.
 *
 * @author mwittig
 *
 */
public class TypeTimeTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Nt", TypeTime.from(null).toQ());
	}

	/** */
	@Test
	public final void testQ() {
		final Time time = new Time(1000 * 60 * 60 * 12);
		assertEquals("12:00:00.000", TypeTime.from(time).toQ());
	}

	/** */
	@Test
	public final void testQ2() {
		assertEquals("12:30:00.500", TypeTime.from(12, 30, 0, 500).toQ());
	}

}
