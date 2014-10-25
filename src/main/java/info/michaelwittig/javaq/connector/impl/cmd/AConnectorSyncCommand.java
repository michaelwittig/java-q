package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.connector.impl.CResultHelper;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * Abstract connector synchronous command.
 * 
 * @author mwittig
 * 
 */
public abstract class AConnectorSyncCommand implements ConnectorSyncCommand {
	
	/**
	 * @param c C
	 * @param q Q
	 * @return Result
	 * @throws QConnectorException If something went wrong
	 * @throws KException If something went wrong
	 * @throws IOException If something went wrong
	 */
	protected final Result execute(final c c, final String q) throws QConnectorException, KException, IOException {
		final Object res = c.k(q);
		return CResultHelper.convert(res);
	}
}
