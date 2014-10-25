package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.KXConnector;

/**
 * KX Connector implementation.
 * 
 * @author mwittig
 * 
 */
abstract class KXConnectorImpl implements KXConnector {
	
	/** Reconnect timeout offset per try. */
	protected static final int RECONNECT_OFFSET_PER_TRY = 1000;
	
	/** Host. */
	private final String host;
	
	/** Port. */
	private final int port;
	
	/** Reconnect on error? */
	private final boolean reconnectOnError;
	
	
	/**
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected KXConnectorImpl(final String aHost, final int aPort, final boolean aReconnectOnError) {
		super();
		this.host = aHost;
		this.port = aPort;
		this.reconnectOnError = aReconnectOnError;
	}
	
	@Override
	public final String getHost() {
		return this.host;
	}
	
	@Override
	public final int getPort() {
		return this.port;
	}
	
	@Override
	public final boolean reconnectOnError() {
		return this.reconnectOnError;
	}
	
}
