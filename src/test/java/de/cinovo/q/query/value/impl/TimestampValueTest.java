// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.value.impl;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;

/**
 * TimestampValue test.
 * 
 * @author mwittig
 * 
 */
public class TimestampValueTest {
	
	/** */
	@Test
	public final void testNullQ() {
		Assert.assertEquals("0Np", TimestampValue.from(null).toQ());
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
		Assert.assertEquals("1970.01.01D12:00:00.000000500", TimestampValue.from(this.create()).toQ());
	}
	
	/** */
	@Test
	public final void testQ2() {
		Assert.assertEquals("2012.02.15D12:30:00.100200300", TimestampValue.from(2012, 2, 15, 12, 30, 0, 100, 200, 300).toQ());
	}
	
	/** */
	@Test
	public final void testListEmpty() {
		Assert.assertEquals("()", TimestampValue.froms(new Timestamp[] {}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithOneItem() {
		Assert.assertEquals("(1970.01.01D12:00:00.000000500)", TimestampValue.froms(new Timestamp[] {this.create()}).toQ());
	}
	
	/** */
	@Test
	public final void testListWithTwoItems() {
		Assert.assertEquals("(1970.01.01D12:00:00.000000500,1970.01.01D13:00:00.000000500)", TimestampValue.froms(new Timestamp[] {this.create(), this.create2()}).toQ());
	}
	
}
