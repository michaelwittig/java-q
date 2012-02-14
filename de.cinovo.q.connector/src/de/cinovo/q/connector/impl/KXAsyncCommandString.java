package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX string command without result.
 *
 * @author mwittig
 *
 */
final class KXAsyncCommandString implements KXAsyncCommand {

	/** Command. */
	private final String cmd;

	/**
	 * @param aCmd Command
	 */
	public KXAsyncCommandString(final String aCmd) {
		super();
		this.cmd = aCmd;
	}

	public String getCmd() {
		return cmd;
	}

	@Override
	public void execute(final c c) throws KException, IOException {
		c.ks(this.cmd);
	}

}
