package de.cinovo.q.connector.impl;

import org.junit.Assert;
import org.junit.Test;

import de.cinovo.q.query.PrimitiveResult;
import de.cinovo.q.query.Result;
import de.cinovo.q.query.TableResult;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 */
@SuppressWarnings("javadoc")
public final class KXconnectorSyncTSImplReconnectTest extends ATest {
	
	@Test
	public void testConnectionFail() throws Exception {
		this.launchQProcess();
		final KXconnectorSyncTSImpl c = new KXconnectorSyncTSImpl("localhost", 5000, false);
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
		final KXconnectorSyncTSImpl c = new KXconnectorSyncTSImpl("localhost", 5000, true);
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
	
	@Test
	public void testReadTable() throws Exception {
		try {
			this.launchQProcess();
			final KXconnectorSyncTSImpl c = new KXconnectorSyncTSImpl("localhost", 5000, true);
			c.connect();
			final TableResult res = (TableResult) c.execute("([] a:(1 2 3 4 5 6); b:(111000b); c:(`a`b`c`d`e`f))");
			Assert.assertEquals(3, res.getCols());
			Assert.assertEquals(6, res.getRows());
			Assert.assertArrayEquals(new String[] {"a", "b", "c"}, res.getColNames());
			Assert.assertEquals(1L, res.getAt(0, 0));
			Assert.assertEquals(1L, res.getAt("a", 0));
		} finally {
			this.terminateQProcess();
		}
	}
	
}
