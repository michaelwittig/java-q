package de.cinovo.q.connector.impl;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 * @param <T> connector type
 */
public abstract class AReconnectTest<T extends KXConnectorImpl> {
	
	/** Path to q executable. */
	public static final String PATH_TO_Q = "/Users/tullius/q/m32/q";
	
	
	void startKDB() throws Exception {
		new ProcessBuilder(AReconnectTest.PATH_TO_Q, "-p", "5000").start();
		Thread.sleep(200);
	}
	
	void stopKDB() throws Exception {
		new ProcessBuilder("killall", "q").start().waitFor();
		Thread.sleep(200);
		
	}
	
}
