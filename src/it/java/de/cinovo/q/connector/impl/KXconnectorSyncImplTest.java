package de.cinovo.q.connector.impl;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 */
public final class KXconnectorSyncImplTest extends ATestWithConnectionSync<KXConnectorSyncImpl> {
	
	@Override
	KXConnectorSyncImpl create(final String host, final int port) {
		return new KXConnectorSyncImpl(host, port, true);
	}
	
}
