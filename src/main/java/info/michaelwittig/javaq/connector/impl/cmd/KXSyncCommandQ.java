package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX Q command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXSyncCommandQ extends AKXSyncCommand {
	
	/** Q. */
	private final String q;
	
	
	/**
	 * @param aQ Q
	 */
	public KXSyncCommandQ(final String aQ) {
		super();
		this.q = aQ;
	}
	
	@Override
	public Result execute(final c c) throws KXException, KException, IOException {
		return this.execute(c, this.q);
	}
	
}
