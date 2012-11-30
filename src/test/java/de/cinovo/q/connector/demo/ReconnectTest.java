// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.demo;

import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.impl.KXConnectorFactory;
import de.cinovo.q.query.Function;
import de.cinovo.q.query.impl.FunctionImpl;
import de.cinovo.q.query.value.impl.SymbolValue;

/**
 * 
 * Copyright 2012 Cinovo AG
 * 
 * @author mwittig
 * 
 */
public class ReconnectTest {
	
	private static final int Q_PORT = 9999;
	
	private static final String Q_PATH = "/Users/tullius/q/m32/q";
	
	private Process qprocess;
	
	
	/**
	 * @param args Args
	 * @throws Exception If the test fails
	 */
	public static void main(String[] args) throws Exception {
		new ReconnectTest().test();
	}
	
	/**
	 * @throws Exception If the test fails
	 */
	public void test() throws Exception {
		try {
			this.startQ();
			this.testSyncTSReconnect();
			this.testSyncTSNoReconnect();
			this.testSyncNotTSReconnect();
			this.testSyncNotTSNoReconnect();
		} finally {
			this.killQ();
		}
	}
	
	/**
	 * @throws Exception If the test fails
	 */
	public void testSyncTSReconnect() throws Exception {
		System.out.println("sync thread-safe and reconnect");
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", ReconnectTest.Q_PORT, true, true);
		try {
			kx.connect();
			this.testSyncReconnect(kx);
		} finally {
			kx.disconnect();
		}
		System.out.println("done");
	}
	
	/**
	 * @throws Exception If the test fails
	 */
	public void testSyncTSNoReconnect() throws Exception {
		System.out.println("sync thread-safe");
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", ReconnectTest.Q_PORT, false, true);
		try {
			kx.connect();
			this.testSyncNoReconnect(kx);
		} finally {
			kx.disconnect();
		}
		System.out.println("done");
	}
	
	/**
	 * @throws Exception If the test fails
	 */
	public void testSyncNotTSReconnect() throws Exception {
		System.out.println("sync not thread-safe and reconnect");
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", ReconnectTest.Q_PORT, true, false);
		try {
			kx.connect();
			this.testSyncReconnect(kx);
		} finally {
			kx.disconnect();
		}
		System.out.println("done");
	}
	
	/**
	 * @throws Exception If the test fails
	 */
	public void testSyncNotTSNoReconnect() throws Exception {
		System.out.println("sync not thread-safe");
		final KXConnectorSync kx = KXConnectorFactory.create("localhost", ReconnectTest.Q_PORT, false, false);
		try {
			kx.connect();
			this.testSyncNoReconnect(kx);
		} finally {
			kx.disconnect();
		}
		System.out.println("done");
	}
	
	/**
	 * @param kx Connection
	 * @throws Exception If the test fails
	 */
	public void testSyncReconnect(final KXConnectorSync kx) throws Exception {
		final Function fn = new FunctionImpl.FunctionBuilderImpl("tables").param(SymbolValue.from(("."))).build();
		kx.call(fn);
		System.out.println("result");
		this.killQ();
		try {
			kx.call(fn);
		} catch (final KXException e) {
			System.out.println("error as expected");
		}
		this.startQ();
		kx.call(fn);
		System.out.println("result");
	}
	
	/**
	 * @param kx Connection
	 * @throws Exception If the test fails
	 */
	public void testSyncNoReconnect(final KXConnectorSync kx) throws Exception {
		final Function fn = new FunctionImpl.FunctionBuilderImpl("tables").param(SymbolValue.from(("."))).build();
		kx.call(fn);
		System.out.println("result");
		this.killQ();
		try {
			kx.call(fn);
		} catch (final KXException e) {
			System.out.println("error as expected");
		}
		this.startQ();
		try {
			kx.call(fn);
			throw new Exception("reconnect was done?");
		} catch (final KXException e) {
			System.out.println("error as expected");
		}
	}
	
	/**
	 * kill q process.
	 */
	public void killQ() {
		System.out.println("kill");
		this.qprocess.destroy();
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e) {
			// nothing to do here
		}
	}
	
	/**
	 * start q process.
	 * 
	 * @throws Exception If the process can not be started
	 */
	public void startQ() throws Exception {
		System.out.println("start");
		this.qprocess = Runtime.getRuntime().exec(ReconnectTest.Q_PATH + " -p " + ReconnectTest.Q_PORT);
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e) {
			// nothing to do here
		}
	}
}
