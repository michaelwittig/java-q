package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.QConnectorAsync;
import info.michaelwittig.javaq.connector.QConnectorError;
import info.michaelwittig.javaq.connector.QConnectorListener;
import info.michaelwittig.javaq.connector.QConnectorSync;
import info.michaelwittig.javaq.connector.QConnectorException;

/**
 * Creates QConnectors.
 * 
 * @author mwittig
 * 
 */
public final class QConnectorFactory {
	
	/**
	 * @param listener Listener
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @param connect Connect?
	 * @throws QConnectorException If the connection can not be established
	 * @throws QConnectorError If the QConnector is already connected
	 * @return QConnector
	 */
	public static QConnectorAsync create(final QConnectorListener listener, final String host, final int port, final boolean reconnectOnError, final boolean connect) throws QConnectorException, QConnectorError {
		final QConnectorAsync c = QConnectorFactory.create(listener, host, port, reconnectOnError);
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
	 * @return QConnector
	 */
	public static QConnectorAsync create(final QConnectorListener listener, final String host, final int port, final boolean reconnectOnError) {
		final QConnectorAsync c = new QConnectorAsyncImpl(listener, host, port, reconnectOnError);
		return c;
	}
	
	/**
	 * @param host Host
	 * @param port Port
	 * @param reconnectOnError Reconnect on error?
	 * @param threadsafe Thread-safe implementation?
	 * @param connect Connect?
	 * @throws QConnectorException If the connection can not be established
	 * @throws QConnectorError If the QConnector is already connected
	 * @return QConnector
	 */
	public static QConnectorSync create(final String host, final int port, final boolean reconnectOnError, final boolean threadsafe, final boolean connect) throws QConnectorException, QConnectorError {
		final QConnectorSync c = QConnectorFactory.create(host, port, reconnectOnError, threadsafe);
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
	 * @return QConnector
	 */
	public static QConnectorSync create(final String host, final int port, final boolean reconnectOnError, final boolean threadsafe) {
		final QConnectorSync c;
		if (threadsafe) {
			c = new QConnectorSyncTSImpl(host, port, reconnectOnError);
		} else {
			c = new QConnectorSyncImpl(host, port, reconnectOnError);
		}
		return c;
	}
	
	/** */
	private QConnectorFactory() {
		super();
	}
	
}
