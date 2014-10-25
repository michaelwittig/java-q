package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX command with result.
 * 
 * @author mwittig
 * 
 */
public interface KXSyncCommand {
	
	/**
	 * Execute the command via c.
	 * 
	 * @param c C
	 * @return Result
	 * @throws KXException If something went wrong
	 * @throws KException If something went wrong in q
	 * @throws IOException If something went wrong on the transport layer
	 */
	Result execute(final c c) throws KXException, KException, IOException;
	
}
