package de.cinovo.q.connector.impl;

import org.junit.Assert;
import org.junit.Test;

import de.cinovo.q.query.PrimitiveResult;
import de.cinovo.q.query.Result;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 */
@SuppressWarnings("javadoc")
public final class KXconnectorSyncTSImplTest extends AReconnectTest<KXconnectorSyncTSImpl> {
	
	@Test
	public void testConnectionFail() throws Exception {
		this.startKDB();
		final KXconnectorSyncTSImpl c = new KXconnectorSyncTSImpl("localhost", 5000, false);
		c.connect();
		Result r1 = c.execute("1+1");
		Assert.assertTrue(r1 instanceof PrimitiveResult);
		this.stopKDB();
		Thread.sleep(1000);
		try {
			c.execute("1+1");
			Assert.fail("Should fail");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		this.startKDB();
		try {
			c.execute("1+1");
			Assert.fail("Should fail");
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			this.stopKDB();
		}
	}
	
	@Test
	public void testConnectionFailWithReconnect() throws Exception {
		this.startKDB();
		final KXconnectorSyncTSImpl c = new KXconnectorSyncTSImpl("localhost", 5000, true);
		c.connect();
		Result r1 = c.execute("1+1");
		Assert.assertTrue(r1 instanceof PrimitiveResult);
		this.stopKDB();
		Thread.sleep(1000);
		try {
			c.execute("1+1");
			Assert.fail("Should fail");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		this.startKDB();
		Result r2 = c.execute("1+1");
		Assert.assertTrue(r2 instanceof PrimitiveResult);
		this.stopKDB();
	}
	
}
