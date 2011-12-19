package de.cinovo.q.connector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import kx.c.KException;;

/**
 * KX Connector implementation.
 *
 * @author mwittig
 *
 */
public final class KXConnectorImpl implements KXConnector {

	/** Socket timeout. */
	private static final int SOCKET_TIMEOUT = 1000;

	/** Listener. */
	private final KXConnectorListener listener;

	/** Host. */
	private final String host;

	/** Port. */
	private final int port;

	/** Reconnect on error? */
	private final boolean reconnectOnError;

	/** Connection. */
	private final AtomicReference<c> c = new AtomicReference<c>();

	/** Callback executor service. */
	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	/**
	 * @param aListener Listener
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected KXConnectorImpl(final KXConnectorListener aListener, final String aHost, final int aPort, final boolean aReconnectOnError) {
		super();
		this.listener = aListener;
		this.host = aHost;
		this.port = aPort;
		this.reconnectOnError = aReconnectOnError;
	}

	@Override
	public void connect() throws KXException, KXError {
		try {
			if (!this.c.compareAndSet(null, new c(this.host, this.port))) {
				throw new KXError("Already connected");
			}
			//this.c.get().s.setSoTimeout(SOCKET_TIMEOUT);
		} catch (final KException e) {
			throw new KXException("KException", e);
		} catch (final IOException e) {
			throw new KXException("Could not connect to " + this.host + ":" + this.port, e);
		}
		new Thread(new Reader()).start();
	}

	private void throwKXException(final KXException e) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				KXConnectorImpl.this.listener.exception(e);
			}
		});
	}

	private void throwKXError(final KXError e) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				KXConnectorImpl.this.listener.error(e);
			}
		});
	}

	private void throwData(final KXTable t) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				KXConnectorImpl.this.listener.dataReceived("", t);
			}
		});
	}

	/**
	 * Reader.
	 *
	 * @author mwittig
	 *
	 */
	private final class Reader implements Runnable {

		/** */
		public Reader() {
			super();
		}

		@Override
		public void run() {
			while (KXConnectorImpl.this.c.get() != null) {
				try {
					final Object res = KXConnectorImpl.this.c.get().k();
					if (res instanceof Object[]) {
						final Object[] tres = (Object[]) res;
						if (tres[1] instanceof c.Flip) {
							final String table = (String) tres[0];
							final c.Flip flip = (c.Flip) tres[1];
							final KXTable t = new KXTableImpl(table, flip.x, flip.y);
							KXConnectorImpl.this.throwData(t);
						} else if (tres[2] instanceof c.Flip) {
							final String cmd = (String) tres[0];
							final String table = (String) tres[1];
							final c.Flip flip = (c.Flip) tres[2];
							final KXTable t = new KXTableImpl(table, flip.x, flip.y);
							KXConnectorImpl.this.throwData(t);
						} else {
							KXConnectorImpl.this.throwKXError(new KXError("Unsupported async resulted type: " + res.getClass().getSimpleName()));
						}
					} else {
						KXConnectorImpl.this.throwKXError(new KXError("Unsupported async resulted type: " + res.getClass().getSimpleName()));
					}
				} catch (final SocketTimeoutException e) {
					continue;
				} catch (final UnsupportedEncodingException e) {
					KXConnectorImpl.this.throwKXException(new KXException("UnsupportedEncodingException", e));
				} catch (final KException e) {
					KXConnectorImpl.this.throwKXException(new KXException("KException", e));
				} catch (final IOException e) {
					KXConnectorImpl.this.throwKXException(new KXException("Could not read from " + KXConnectorImpl.this.host + ":"
							+ KXConnectorImpl.this.port, e));
				}
			}
		}
	}

	@Override
	public void disconnect() throws KXError {
		final c old = this.c.get();
		if (!this.c.compareAndSet(old, null)) {
			throw new KXError("Already disconnect");
		}
		try {
			old.close();
		} catch (final IOException e) {
			e.printStackTrace(); // TODO suppress
		}
	}

	@Override
	public void subscribe(final String handle, final String[] tables, final String[] symbols) throws KXException {
		final StringBuilder t = new StringBuilder();
		for (final String table : tables) {
			t.append("`");
			t.append(table);
		}
		final StringBuilder s = new StringBuilder();
		for (final String symbol : symbols) {
			s.append("`");
			s.append(symbol);
		}
		try {
			final Object res = this.c.get().k(".u.sub[" + t.toString() + ";" + s.toString() + "]");
		} catch (final KException e) {
			throw new KXException("KException", e);
		} catch (final IOException e) {
			throw new KXException("Could not talk to " + this.host + ":" + this.port, e);
		}
	}

	@Override
	public void subscribe(final KXDataListener listener, final String[] tables, final String[] symbols) throws KXException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unsubscribe(final String handle) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unsubscribe(final KXDataListener listener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public KXTable sync(final String cmd) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void async(final String handle, final String cmd) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void async(final KXDataListener listener, final String cmd) {
		throw new UnsupportedOperationException();
	}

	@Override
	public KXConnectorListener getConnectorListener() {
		return this.listener;
	}

	@Override
	public String getHost() {
		return this.host;
	}

	@Override
	public int getPort() {
		return this.port;
	}

	@Override
	public boolean reconnectOnError() {
		return this.reconnectOnError;
	}

	@Override
	public boolean isConnected() {
		if (this.c.get() != null) {
			return true;
		}
		return false;
	}

}
