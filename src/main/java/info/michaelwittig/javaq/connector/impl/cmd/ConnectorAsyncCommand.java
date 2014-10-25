package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.QConnectorException;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * Connector command without result.
 * 
 * @author mwittig
 * 
 */
public interface ConnectorAsyncCommand {
	
	/**
	 * Execute the command via c.
	 * 
	 * @param c C
	 * @throws QConnectorException If something went wrong
	 * @throws KException If something went wrong in q
	 * @throws IOException If something went wrong on the transport layer
	 */
	void execute(final c c) throws QConnectorException, KException, IOException;
	
}
