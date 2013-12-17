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
public final class KXConnectorSyncImplReconnectTest extends ATest {
	
	@Test
	public void testConnectionFail() throws Exception {
		this.launchQProcess();
		final KXConnectorSyncImpl c = new KXConnectorSyncImpl("localhost", 5000, false);
		c.connect();
		Result r1 = c.execute("1+1");
		Assert.assertTrue(r1 instanceof PrimitiveResult);
		this.terminateQProcess();
		Thread.sleep(1000);
		try {
			c.execute("1+1");
			Assert.fail("Should fail");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		this.launchQProcess();
		try {
			c.execute("1+1");
			Assert.fail("Should fail");
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			this.terminateQProcess();
		}
	}
	
	@Test
	public void testConnectionFailWithReconnect() throws Exception {
		this.launchQProcess();
		final KXConnectorSyncImpl c = new KXConnectorSyncImpl("localhost", 5000, true);
		c.connect();
		Result r1 = c.execute("1+1");
		Assert.assertTrue(r1 instanceof PrimitiveResult);
		this.terminateQProcess();
		Thread.sleep(1000);
		try {
			c.execute("1+1");
			Assert.fail("Should fail");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		this.launchQProcess();
		Result r2 = c.execute("1+1");
		Assert.assertTrue(r2 instanceof PrimitiveResult);
		this.terminateQProcess();
	}
}
