package info.michaelwittig.javaq.connector.impl;


/**
 * @author mwittig
 * 
 */
public final class KXconnectorSyncImplTest extends ATestWithConnectionSync<KXConnectorSyncImpl> {
	
	@Override
	KXConnectorSyncImpl create(final String host, final int port) {
		return new KXConnectorSyncImpl(host, port, true);
	}
	
}
