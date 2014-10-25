package info.michaelwittig.javaq.connector.impl;


/**
 * @author mwittig
 * 
 */
public final class KXconnectorSyncTSImplTest extends ATestWithConnectionSync<KXconnectorSyncTSImpl> {
	
	@Override
	KXconnectorSyncTSImpl create(final String host, final int port) {
		return new KXconnectorSyncTSImpl(host, port, true);
	}
	
}
