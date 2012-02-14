package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXException;

/**
 * KX command without result.
 *
 * @author mwittig
 *
 */
interface KXAsyncCommand {

	/**
	 * Execute the command via c.
	 *
	 * @param c C
	 * @throws KXException If something went wrong
	 * @throws KException If something went wrong in q
	 * @throws IOException If something went wrong on the transport layer
	 */
	void execute(final c c) throws KXException, KException, IOException;

}
