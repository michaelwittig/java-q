package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * Connector function command with result.
 * 
 * @author mwittig
 * 
 */
public final class ConnectorSyncCommandFunction extends AConnectorSyncCommand {
	
	/** Function. */
	private final Function function;
	
	
	/**
	 * @param aFunction Function
	 */
	public ConnectorSyncCommandFunction(final Function aFunction) {
		super();
		this.function = aFunction;
	}
	
	@Override
	public Result execute(final c c) throws QConnectorException, KException, IOException {
		return this.execute(c, this.function.toQ());
	}
	
}
