package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.KXException;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX command without result.
 * 
 * @author mwittig
 * 
 */
public interface KXAsyncCommand {
	
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
