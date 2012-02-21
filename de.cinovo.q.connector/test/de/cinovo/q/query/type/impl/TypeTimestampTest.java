// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.type.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;

/**
 * TypeTimestamp test.
 *
 * @author mwittig
 *
 */
public class TypeTimestampTest {

	/** */
	@Test
	public final void testNullQ() {
		assertEquals("0Np", TypeTimestamp.from(null).toQ());
	}

	/**
	 * @return Timestamp
	 */
	private Timestamp create() {
		// Milliseconds after january 1, 1970 00:00:00 GMT
		final long time = 1000 * 60 * 60 * 12;
		final Timestamp timestamp = new Timestamp(time);
		timestamp.setNanos(500);
		return timestamp;
	}

	/**
	 * @return Timestamp
	 */
	private Timestamp create2() {
		// Milliseconds after january 1, 1970 00:00:00 GMT
		final long time = 1000 * 60 * 60 * 13;
		final Timestamp timestamp = new Timestamp(time);
		timestamp.setNanos(500);
		return timestamp;
	}

	/** */
	@Test
	public final void testQ() {
		assertEquals("1970.01.01D12:00:00.000000500", TypeTimestamp.from(this.create()).toQ());
	}

	/** */
	@Test
	public final void testQ2() {
		assertEquals("2012.02.15D12:30:00.100200300", TypeTimestamp.from(2012, 2, 15, 12, 30, 0, 100, 200, 300).toQ());
	}

	/** */
	@Test
	public final void testListEmpty() {
		assertEquals("()", TypeTimestamp.froms(new Timestamp[] {}).toQ());
	}

	/** */
	@Test
	public final void testListWithOneItem() {
		assertEquals("(1970.01.01D12:00:00.000000500)", TypeTimestamp.froms(new Timestamp[] {this.create()}).toQ());
	}

	/** */
	@Test
	public final void testListWithTwoItems() {
		assertEquals("(1970.01.01D12:00:00.000000500,1970.01.01D13:00:00.000000500)",
				TypeTimestamp.froms(new Timestamp[] {this.create(), this.create2()}).toQ());
	}

}
