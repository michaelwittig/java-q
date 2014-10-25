package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.query.PrimitiveResult;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.TableResult;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mwittig
 * 
 */
@SuppressWarnings("javadoc")
public final class QConnectorSyncTSImplReconnectTest extends ATest {
	
	@Test
	public void testConnectionFail() throws Exception {
		this.launchQProcess();
		final QConnectorSyncTSImpl c = new QConnectorSyncTSImpl("localhost", 5000, false);
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
		final QConnectorSyncTSImpl c = new QConnectorSyncTSImpl("localhost", 5000, true);
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
			final QConnectorSyncTSImpl c = new QConnectorSyncTSImpl("localhost", 5000, true);
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
