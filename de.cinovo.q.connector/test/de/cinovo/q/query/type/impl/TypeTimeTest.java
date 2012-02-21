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

	/**
	 * @return Timestamp
	 */
	private Time create() {
		final Time time = new Time(1000 * 60 * 60 * 12);
		return time;
	}

	/**
	 * @return Timestamp
	 */
	private Time create2() {
		final Time time = new Time(1000 * 60 * 60 * 13);
		return time;
	}

	/** */
	@Test
	public final void testQ() {
		assertEquals("12:00:00.000", TypeTime.from(this.create()).toQ());
	}

	/** */
	@Test
	public final void testQ2() {
		assertEquals("12:30:00.500", TypeTime.from(12, 30, 0, 500).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", TypeTime.froms(new Time[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(12:00:00.000)", TypeTime.froms(new Time[] {this.create()}).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(12:00:00.000,13:00:00.000)",
				TypeTime.froms(new Time[] {this.create(), this.create2()}).toQ());
	}

}
