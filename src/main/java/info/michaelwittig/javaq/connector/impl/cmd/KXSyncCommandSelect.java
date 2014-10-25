package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX select command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXSyncCommandSelect extends AKXSyncCommand {
	
	/** Select. */
	private final Select select;
	
	
	/**
	 * @param aSelect Select
	 */
	public KXSyncCommandSelect(final Select aSelect) {
		super();
		this.select = aSelect;
	}
	
	@Override
	public Result execute(final c c) throws KXException, KException, IOException {
		return this.execute(c, this.select.toQ());
	}
	
}
