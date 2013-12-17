package de.cinovo.q.connector.impl;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 */
public abstract class ATest {
	
	/** Path to q executable. */
	public static final String PATH_TO_Q = "/Users/tullius/q/m32/q";
	
	
	protected void launchQProcess() throws Exception {
		this.launchQProcess(5000);
	}
	
	protected void launchQProcess(final int port) throws Exception {
		// TODO check if the port is available
		new ProcessBuilder(ATest.PATH_TO_Q, "-p", String.valueOf(port)).start();
		Thread.sleep(200);
	}
	
	protected void terminateQProcess() throws Exception {
		this.terminateQProcess(5000);
	}
	
	protected void terminateQProcess(final int port) throws Exception {
		// TODO search the right q process to kill
		new ProcessBuilder("killall", "q").start().waitFor();
		Thread.sleep(200);
	}
	
}
