package de.cinovo.q.connector.impl;

import org.junit.Assert;
import org.junit.Test;

import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.KXListener;
import de.cinovo.q.query.Result;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 */
@SuppressWarnings("javadoc")
public final class KXConnectorAsyncImplTest extends AReconnectTest<KXConnectorAsyncImpl> {
	
	@Test
	public void testConnectionFail() throws Exception {
		this.startKDB();
		final KXConnectorAsyncImpl c = new KXConnectorAsyncImpl(new KXListener() {
			
			@Override
			public void error(KXError e) {
				// reports about reconnects
			}
			
			@Override
			public void exception(KXException e) {
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
		this.stopKDB();
		Thread.sleep(1500);
		try {
			c.disconnect();
			Assert.fail("should fail because disconnect is already triggert");
		} catch (final Exception e) {
			// nothing
		}
		this.startKDB();
		c.connect();
		Thread.sleep(500);
		c.disconnect();
		this.stopKDB();
	}
	
	@Test()
	public void testConnectionFailWithReconnect() throws Exception {
		this.startKDB();
		final KXConnectorAsyncImpl c = new KXConnectorAsyncImpl(new KXListener() {
			
			@Override
			public void error(KXError e) {
				// reports about reconnects
			}
			
			@Override
			public void exception(KXException e) {
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
		this.stopKDB();
		Thread.sleep(3000);
		this.startKDB();
		Thread.sleep(1500);
		c.disconnect();
		this.stopKDB();
	}
}
