// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Time;

import org.junit.Test;

/**
 * TimeValue test.
 * 
 * @author mwittig
 * 
 */
public class TimeValueTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Nt", TimeValue.from(null).toQ());
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
		assertEquals("12:00:00.000", TimeValue.from(this.create()).toQ());
	}

	/** */
	@Test
	public final void testQ2() {
		assertEquals("12:30:00.500", TimeValue.from(12, 30, 0, 500).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", TimeValue.froms(new Time[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(12:00:00.000)", TimeValue.froms(new Time[] { this.create() }).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(12:00:00.000,13:00:00.000)", TimeValue.froms(new Time[] { this.create(), this.create2() }).toQ());
	}

}
