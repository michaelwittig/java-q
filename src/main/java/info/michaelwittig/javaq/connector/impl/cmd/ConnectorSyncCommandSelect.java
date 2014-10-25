package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * Connector select command with result.
 * 
 * @author mwittig
 * 
 */
public final class ConnectorSyncCommandSelect extends AConnectorSyncCommand {
	
	/** Select. */
	private final Select select;
	
	
	/**
	 * @param aSelect Select
	 */
	public ConnectorSyncCommandSelect(final Select aSelect) {
		super();
		this.select = aSelect;
	}
	
	@Override
	public Result execute(final c c) throws QConnectorException, KException, IOException {
		return this.execute(c, this.select.toQ());
	}
	
}
