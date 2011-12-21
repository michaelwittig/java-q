package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX command.
 *
 * @author mwittig
 *
 */
abstract class KXCommand {

	/**
	 * Execute the command via c.
	 *
	 * @param c C
	 * @return Result
	 * @throws KException If something went wrong in q
	 * @throws IOException If something went wrong on the transport layer
	 */
	abstract Object execute(final c c) throws KException, IOException;

}
