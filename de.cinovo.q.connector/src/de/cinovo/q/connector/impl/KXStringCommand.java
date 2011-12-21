package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX string command.
 *
 * @author mwittig
 *
 */
final class KXStringCommand extends KXCommand {

	/** Command. */
	private final String cmd;

	/**
	 * @param aCmd Command
	 */
	public KXStringCommand(final String aCmd) {
		super();
		this.cmd = aCmd;
	}

	public String getCmd() {
		return cmd;
	}

	@Override
	Object execute(final c c) throws KException, IOException {
		return c.k(this.cmd);
	}

}
