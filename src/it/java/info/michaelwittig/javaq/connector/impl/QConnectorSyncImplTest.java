package info.michaelwittig.javaq.connector.impl;

/**
 * @author mwittig
 * 
 */
public final class QConnectorSyncImplTest extends ATestWithConnectionSync<QConnectorSyncImpl> {
	
	@Override
	QConnectorSyncImpl create(final String host, final int port) {
		return new QConnectorSyncImpl(host, port, true);
	}
	
}
