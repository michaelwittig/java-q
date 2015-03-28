package info.michaelwittig.javaq.connector.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import org.apache.commons.lang3.SystemUtils;

/**
 * Assumes the ENV QHOME to be set!
 *
 * @author mwittig
 * @author bdupreez.. added windows support
 */
public abstract class ATest {

	private static final Map<Integer, Integer> port2pid = new HashMap<Integer, Integer>();
	private static final String DEFAULT_QHOME = "C:/q";

	protected void launchQProcess() throws Exception {
		this.launchQProcess(5000);
	}

	protected synchronized void launchQProcess(final int port) throws Exception {
		String qhome = System.getenv("QHOME");
		if (qhome == null) {
			qhome = DEFAULT_QHOME;
		}
		if (ATest.port2pid.containsKey(port)) {
			throw new RuntimeException("port already used");
		}
		final String path;
		if (SystemUtils.IS_OS_LINUX) {
			path = qhome + "/l32/q";
		} else if (SystemUtils.IS_OS_MAC) {
			path = qhome + "/m32/q";
		} else if (SystemUtils.IS_OS_WINDOWS) {
			path = qhome + "/w32/q";
		} else {
			throw new RuntimeException("OS not supported!");
		}
		final Process p = new ProcessBuilder(path, "-p", String.valueOf(port)).start();
		final int pid;
		try {
			if (SystemUtils.IS_OS_WINDOWS) {
				pid = determineWindowsProcessId(p);
			} else {
				pid = getLinuxMacPid(p);
			}
		} catch (final Exception e) {
			throw new RuntimeException("OS related exception!", e);
		}
		ATest.port2pid.put(port, pid);
		Thread.sleep(200); // wait until started
	}

	private int getLinuxMacPid(final Process p) throws NoSuchFieldException, IllegalAccessException {
		final Field f = p.getClass().getDeclaredField("pid");
		f.setAccessible(true);
		return f.getInt(p);
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
		if (SystemUtils.IS_OS_WINDOWS) {
			new ProcessBuilder("taskkill", "/PID", pid.toString(), "/T", "/F").start().waitFor();
		}else {
			new ProcessBuilder("kill", "-9", pid.toString()).start().waitFor();
		}
	}

	private int determineWindowsProcessId(final Process proc) {
		if (proc.getClass().getName().equals("java.lang.Win32Process")
				|| proc.getClass().getName().equals("java.lang.ProcessImpl")) {
			try {
				final Field f = proc.getClass().getDeclaredField("handle");
				f.setAccessible(true);
				final long handleId = f.getLong(proc);
				final Kernel32 kernel = Kernel32.INSTANCE;
				final WinNT.HANDLE handle = new WinNT.HANDLE();
				handle.setPointer(Pointer.createConstant(handleId));
				return kernel.GetProcessId(handle);
			} catch (final Throwable e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return 0;
	}

}
