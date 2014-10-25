package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.connector.impl.KXResultHelper;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * Abstract kx synchronous command.
 * 
 * @author mwittig
 * 
 */
public abstract class AKXSyncCommand implements KXSyncCommand {
	
	/**
	 * @param c C
	 * @param q Q
	 * @return Result
	 * @throws KXException If something went wrong
	 * @throws KException If something went wrong
	 * @throws IOException If something went wrong
	 */
	protected final Result execute(final c c, final String q) throws KXException, KException, IOException {
		final Object res = c.k(q);
		return KXResultHelper.convert(res);
	}
}
