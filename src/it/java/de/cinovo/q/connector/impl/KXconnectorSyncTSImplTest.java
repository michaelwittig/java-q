package de.cinovo.q.connector.impl;

/**
 * 
 * Copyright 2013 Cinovo AG
 * 
 * @author mwittig
 * 
 */
public final class KXconnectorSyncTSImplTest extends ATestWithConnectionSync<KXconnectorSyncTSImpl> {
	
	@Override
	KXconnectorSyncTSImpl create(final String host, final int port) {
		return new KXconnectorSyncTSImpl(host, port, true);
	}
	
}
