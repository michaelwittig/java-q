package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX function command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXSyncCommandFunction extends AKXSyncCommand {
	
	/** Function. */
	private final Function function;
	
	
	/**
	 * @param aFunction Function
	 */
	public KXSyncCommandFunction(final Function aFunction) {
		super();
		this.function = aFunction;
	}
	
	@Override
	public Result execute(final c c) throws KXException, KException, IOException {
		return this.execute(c, this.function.toQ());
	}
	
}
