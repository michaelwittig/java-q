package info.michaelwittig.javaq.connector.impl;

/**
 * @author mwittig
 * 
 */
public final class QConnectorSyncTSImplTest extends ATestWithConnectionSync<QConnectorSyncTSImpl> {
	
	@Override
	QConnectorSyncTSImpl create(final String host, final int port) {
		return new QConnectorSyncTSImpl(host, port, true);
	}
	
}
