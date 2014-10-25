package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.QConnectorError;
import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.connector.QConnectorListener;
import info.michaelwittig.javaq.query.Result;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mwittig
 * 
 */
@SuppressWarnings("javadoc")
public final class QConnectorAsyncImplReconnectTest extends ATest {
	
	@Test
	public void testConnectionFail() throws Exception {
		this.launchQProcess();
		final QConnectorAsyncImpl c = new QConnectorAsyncImpl(new QConnectorListener() {
			
			@Override
			public void error(QConnectorError e) {
				// reports about reconnects
			}
			
			@Override
			public void exception(QConnectorException e) {
				// reports about exceptions
			}
			
			@Override
			public void resultReceived(final String handle, final Result result) {
				// data received
			}
		}, "localhost", 5000, false);
		c.connect();
		c.subscribe("test", new String[] {"test"}, new String[] {"TEST"});
		Thread.sleep(1500);
		this.terminateQProcess();
		Thread.sleep(1500);
		try {
			c.disconnect();
			Assert.fail("should fail because disconnect is already triggert");
		} catch (final Exception e) {
			// nothing
		}
		this.launchQProcess();
		c.connect();
		Thread.sleep(500);
		c.disconnect();
		this.terminateQProcess();
	}
	
	@Test()
	public void testConnectionFailWithReconnect() throws Exception {
		this.launchQProcess();
		final QConnectorAsyncImpl c = new QConnectorAsyncImpl(new QConnectorListener() {
			
			@Override
			public void error(QConnectorError e) {
				// reports about reconnects
			}
			
			@Override
			public void exception(QConnectorException e) {
				throw new RuntimeException(e);
			}
			
			@Override
			public void resultReceived(final String handle, final Result result) {
				// data received
			}
		}, "localhost", 5000, true);
		c.connect();
		c.subscribe("test", new String[] {"test"}, new String[] {"TEST"});
		Thread.sleep(1500);
		this.terminateQProcess();
		Thread.sleep(3000);
		this.launchQProcess();
		Thread.sleep(1500);
		c.disconnect();
		this.terminateQProcess();
	}
}
