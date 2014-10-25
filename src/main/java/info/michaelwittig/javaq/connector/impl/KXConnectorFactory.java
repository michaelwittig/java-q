package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.KXConnectorAsync;
import info.michaelwittig.javaq.connector.KXConnectorSync;
import info.michaelwittig.javaq.connector.KXError;
import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.connector.KXListener;

/**
 * Creates KXConnectors.
 * 
 * @author mwittig
 * 
 */
public final class KXConnectorFactory {
	
	/**
	 * @param listener Listener
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @param connect Connect?
	 * @throws KXException If the connection can not be established
	 * @throws KXError If the KXConnector is already connected
	 * @return KXConnector
	 */
	public static KXConnectorAsync create(final KXListener listener, final String host, final int port, final boolean reconnectOnError, final boolean connect) throws KXException, KXError {
		final KXConnectorAsync c = KXConnectorFactory.create(listener, host, port, reconnectOnError);
		if (connect) {
			c.connect();
		}
		return c;
	}
	
	/**
	 * @param listener Listener
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @return KXConnector
	 */
	public static KXConnectorAsync create(final KXListener listener, final String host, final int port, final boolean reconnectOnError) {
		final KXConnectorAsync c = new KXConnectorAsyncImpl(listener, host, port, reconnectOnError);
		return c;
	}
	
	/**
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @param threadsafe Thread-safe implementation?
	 * @param connect Connect?
	 * @throws KXException If the connection can not be established
	 * @throws KXError If the KXConnector is already connected
	 * @return KXConnector
	 */
	public static KXConnectorSync create(final String host, final int port, final boolean reconnectOnError, final boolean threadsafe, final boolean connect) throws KXException, KXError {
		final KXConnectorSync c = KXConnectorFactory.create(host, port, reconnectOnError, threadsafe);
		if (connect) {
			c.connect();
		}
		return c;
	}
	
	/**
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @param threadsafe Thread-safe implementation?
	 * @return KXConnector
	 */
	public static KXConnectorSync create(final String host, final int port, final boolean reconnectOnError, final boolean threadsafe) {
		final KXConnectorSync c;
		if (threadsafe) {
			c = new KXconnectorSyncTSImpl(host, port, reconnectOnError);
		} else {
			c = new KXConnectorSyncImpl(host, port, reconnectOnError);
		}
		return c;
	}
	
	/** */
	private KXConnectorFactory() {
		super();
	}
	
}
