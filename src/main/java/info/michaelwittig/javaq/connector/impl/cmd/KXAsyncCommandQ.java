package info.michaelwittig.javaq.connector.impl.cmd;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX Q command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXAsyncCommandQ implements KXAsyncCommand {
	
	/** Q. */
	private final String q;
	
	
	/**
	 * @param aQ Q
	 */
	public KXAsyncCommandQ(final String aQ) {
		super();
		this.q = aQ;
	}
	
	@Override
	public void execute(final c c) throws KException, IOException {
		c.ks(this.q);
	}
	
}
