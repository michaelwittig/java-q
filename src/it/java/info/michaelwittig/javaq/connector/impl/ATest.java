package info.michaelwittig.javaq.connector.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

/**
 * Assumes the ENV QHOME to be set!
 * 
 * @author mwittig
 * 
 */
public abstract class ATest {
	
	private static final Map<Integer, Integer> port2pid = new HashMap<Integer, Integer>();
	
	
	protected void launchQProcess() throws Exception {
		this.launchQProcess(5000);
	}
	
	protected synchronized void launchQProcess(final int port) throws Exception {
		final String qhome = System.getenv("QHOME");
		if (qhome == null) {
			throw new RuntimeException("ENV QHOME is not defined!");
		}
		if (ATest.port2pid.containsKey(port)) {
			throw new RuntimeException("port already used");
		}
		final String path;
		if (SystemUtils.IS_OS_LINUX) {
			path = qhome + "/l32/q";
		} else if (SystemUtils.IS_OS_MAC) {
			path = qhome + "/m32/q";
		} else {
			throw new RuntimeException("Only linux or mac is supported!");
		}
		final Process p = new ProcessBuilder(path, "-p", String.valueOf(port)).start();
		final int pid;
		try {
			final Field f = p.getClass().getDeclaredField("pid");
			f.setAccessible(true);
			pid = (Integer) f.get(p);
		} catch (final Exception e) {
			throw new RuntimeException("Only linux or mac is supported!", e);
		}
		ATest.port2pid.put(port, pid);
		Thread.sleep(200); // wait until started
	}
	
	protected void terminateQProcess() throws Exception {
		this.terminateQProcess(5000);
	}
	
	protected void terminateQProcess(final int port) throws Exception {
		final Integer pid = ATest.port2pid.get(port);
		ATest.port2pid.remove(port);
		if (pid == null) {
			throw new RuntimeException("port not used");
		}
		new ProcessBuilder("kill", "-9", pid.toString()).start().waitFor();
	}
	
}
