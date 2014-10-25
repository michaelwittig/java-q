package info.michaelwittig.javaq.connector.impl.cmd;

import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * Connector Q command with result.
 * 
 * @author mwittig
 * 
 */
public final class ConnectorSyncCommandQ extends AConnectorSyncCommand {
	
	/** Q. */
	private final String q;
	
	
	/**
	 * @param aQ Q
	 */
	public ConnectorSyncCommandQ(final String aQ) {
		super();
		this.q = aQ;
	}
	
	@Override
	public Result execute(final c c) throws QConnectorException, KException, IOException {
		return this.execute(c, this.q);
	}
	
}
