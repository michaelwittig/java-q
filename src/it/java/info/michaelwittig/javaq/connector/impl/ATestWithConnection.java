package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.QConnector;

import org.junit.After;
import org.junit.Before;

/**
 * Assumes running q process on localhost:5000!
 * 
 * @author mwittig
 * 
 * @param <T> QConnector
 * 
 */
@SuppressWarnings("javadoc")
public abstract class ATestWithConnection<T extends QConnector> extends ATest {
	
	private volatile T c;
	
	
	abstract T create(String host, int port);
	
	@Before
	public void setUp() throws Exception {
		this.launchQProcess();
		this.c = this.create("localhost", 5000);
		this.c.connect();
	}
	
	@After
	public void tearDOwn() throws Exception {
		this.terminateQProcess();
	}
	
	protected T c() {
		return this.c;
	}
	
}
