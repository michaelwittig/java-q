// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXConnectorAsync;
import de.cinovo.q.connector.KXDataListener;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.KXListener;
import de.cinovo.q.connector.KXTable;

/**
 * KX Connector implementation.
 *
 * @author mwittig
 *
 */
final class KXConnectorAsyncImpl extends KXConnectorImpl implements KXConnectorAsync {

	/** Reconnect tiomeout offset per try. */
	private static final int RECONNECT_OFFSET_PER_TRY = 1000;

	/** Stop comand. */
	private static final KXAsyncCommandString STOP_COMMAND = new KXAsyncCommandString("");

	/** Listener. */
	private final KXListener listener;

	/** Connection. */
	private final AtomicReference<c> c = new AtomicReference<c>();

	/** Callback executor service. */
	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	/** Timer. */
	private final Timer timer = new Timer();

	/** Reconnect counter. */
	private final AtomicInteger reconnectCounter = new AtomicInteger(0);

	/**
	 * @param aListener Listener
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected KXConnectorAsyncImpl(final KXListener aListener, final String aHost, final int aPort, final boolean aReconnectOnError) {
		super(aHost, aPort, aReconnectOnError);
		this.listener = aListener;
	}

	@Override
	public void connect() throws KXException, KXError {
		try {
			if (!this.c.compareAndSet(null, new c(this.getHost(), this.getPort()))) {
				throw new KXError("Already connected");
			}
			new Thread(new Reader()).start();
			new Thread(new Executor(this.c.get())).start();
		} catch (final KException e) {
			throw new KXException("KException", e);
		} catch (final IOException e) {
			if (this.reconnectOnError()) {
				this.throwKXError(new KXError("Could not connect to " + this.getHost() + ":" + this.getPort()));
				this.reconnect();
			} else {
				throw new KXException("Could not connect to " + this.getHost() + ":" + this.getPort(), e);
			}
		}
	}

	@Override
	public void disconnect() throws KXError {
		final c old = this.c.get();
		if (old == null) {
			throw new KXError("Not connected");
		}
		if (!this.c.compareAndSet(old, null)) {
			throw new KXError("Already disconnected");
		}
		this.commands.offer(STOP_COMMAND);
		try {
			old.close();
		} catch (final IOException e) {
			// e.printStackTrace(); // TODO suppress
		}
	}

	@Override
	public void subscribe(final KXDataListener aListener, final String[] tables, final String[] symbols) throws KXException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void subscribe(final String handle, final String[] tables, final String[] symbols) throws KXException {
		final StringBuilder t = new StringBuilder();
		if (tables.length > 0) {
			for (final String table : tables) {
				t.append("`");
				t.append(table);
			}
		} else {
			t.append("`");
		}
		final StringBuilder s = new StringBuilder();
		if (symbols.length > 0) {
			for (final String symbol : symbols) {
				s.append("`");
				s.append(symbol);
			}
		} else {
			s.append("`");
		}
		this.execute(new KXAsyncCommandString(".u.sub[" + t.toString() + ";" + s.toString() + "]"));
	}

	@Override
	public void unsubscribe(final String handle) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unsubscribe(final KXDataListener aListener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void query(final String handle, final String cmd) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void query(final KXDataListener listener, final String cmd) {
		throw new UnsupportedOperationException();
	}

	@Override
	public KXListener getConnectorListener() {
		return this.listener;
	}

	/** Commands. */
	private final BlockingQueue<KXAsyncCommand> commands = new LinkedBlockingQueue<KXAsyncCommand>();

	/**
	 * Write to kx.
	 *
	 * @param cmd Command
	 */
	private void execute(final KXAsyncCommand cmd) {
		this.commands.offer(cmd);
	}

	/**
	 * Reconnect.
	 */
	private void reconnect() {
		// TODO check if reconnection is already active
		try {
			KXConnectorAsyncImpl.this.disconnect();
		} catch (final KXError e) {
			// e.printStackTrace(); // TODO suppress, because this just happens if the connection is not established
		}

		final int count = KXConnectorAsyncImpl.this.reconnectCounter.incrementAndGet();
		final long time = System.currentTimeMillis();
		this.timer.schedule(new ReconnectTask(count), new Date(time + count * RECONNECT_OFFSET_PER_TRY));
	}

	/**
	 * Throw an exception if something happened, that the system can not fix.
	 *
	 * @param e Exception
	 */
	private void throwKXException(final KXException e) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				KXConnectorAsyncImpl.this.listener.exception(e);
			}
		});
	}

	/**
	 * Throw an error if something happened, that the system can fix on his own, but is might important to know.
	 *
	 * @param e Error
	 */
	private void throwKXError(final KXError e) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				KXConnectorAsyncImpl.this.listener.error(e);
			}
		});
	}

	/**
	 * Throw data.
	 *
	 * @param t table
	 */
	private void throwData(final KXTable t) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				KXConnectorAsyncImpl.this.listener.dataReceived("", t);
			}
		});
	}

	/**
	 * Reconnect task.
	 *
	 * @author mwittig
	 *
	 */
	private final class ReconnectTask extends TimerTask {

		/** Count. */
		private final int count;

		/**
		 * @param aCount Count
		 */
		public ReconnectTask(final int aCount) {
			super();
			this.count = aCount;
		}

		@Override
		public void run() {
			try {
				KXConnectorAsyncImpl.this.connect();
			} catch (final KXException e) {
				KXConnectorAsyncImpl.this.throwKXError(new KXError("Reconnect #" + this.count + " failed"));
				e.printStackTrace();
			} catch (final KXError e) {
				// e.printStackTrace(); // TODO suppress, because this just happens if the connection is already established
			}
		}
	}

	/**
	 * Executor.
	 *
	 * @author mwittig
	 *
	 */
	private final class Executor implements Runnable {

		/** C. */
		private final c c;

		/**
		 * @param aC C
		 */
		public Executor(final c aC) {
			this.c = aC;
		}

		@Override
		public void run() {
			while (true) {
				final KXAsyncCommand cmd;
				try {
					cmd = KXConnectorAsyncImpl.this.commands.take();
				} catch (final InterruptedException e) {
					continue;
				}
				if (cmd == STOP_COMMAND) {
					break;
				}
				try {
					cmd.execute(this.c);
				} catch (final KXException e) {
					KXConnectorAsyncImpl.this.throwKXException(e);
				} catch (final KException e) {
					KXConnectorAsyncImpl.this.throwKXException(new KXException("KException", e));
				} catch (final IOException e) {
					if (KXConnectorAsyncImpl.this.reconnectOnError()) {
						KXConnectorAsyncImpl.this.commands.offer(cmd);
						KXConnectorAsyncImpl.this.throwKXError(new KXError("Could not talk to " + KXConnectorAsyncImpl.this.getHost()
								+ ":" + KXConnectorAsyncImpl.this.getPort()));
						KXConnectorAsyncImpl.this.reconnect();
						break;
					} else {
						KXConnectorAsyncImpl.this.throwKXException(new KXException("Could not talk to " + KXConnectorAsyncImpl.this.getHost()
								+ ":" + KXConnectorAsyncImpl.this.getPort(), e));
					}
				}
			}
			System.out.println("stop" + this);
		}
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
			KXConnectorAsyncImpl.this.reconnectCounter.set(0);
			while (KXConnectorAsyncImpl.this.c.get() != null) {
				try {
					final Object res = KXConnectorAsyncImpl.this.c.get().k();
					if (res == null) {
						// nothing to do here
						continue;
					} else if (res instanceof c.Flip) {
						final c.Flip flip = (c.Flip) res;
						final KXTable t = new KXTableImpl("", flip.x, flip.y);
						KXConnectorAsyncImpl.this.throwData(t);
					} else if (res instanceof Object[]) {
						final Object[] tres = (Object[]) res;
						if (tres[1] instanceof c.Flip) {
							final String table = (String) tres[0];
							final c.Flip flip = (c.Flip) tres[1];
							final KXTable t = new KXTableImpl(table, flip.x, flip.y);
							KXConnectorAsyncImpl.this.throwData(t);
						} else if (tres[2] instanceof c.Flip) {
							// final String cmd = (String) tres[0];
							final String table = (String) tres[1];
							final c.Flip flip = (c.Flip) tres[2];
							final KXTable t = new KXTableImpl(table, flip.x, flip.y);
							KXConnectorAsyncImpl.this.throwData(t);
						} else {
							KXConnectorAsyncImpl.this.throwKXError(new KXError("Unsupported async resulted type: " + res.getClass().getSimpleName()));
						}
					} else {
						KXConnectorAsyncImpl.this.throwKXError(new KXError("Unsupported async resulted type: " + res.getClass().getSimpleName()));
					}
				} catch (final SocketTimeoutException e) {
					continue;
				} catch (final UnsupportedEncodingException e) {
					KXConnectorAsyncImpl.this.throwKXException(new KXException("UnsupportedEncodingException", e));
				} catch (final KException e) {
					KXConnectorAsyncImpl.this.throwKXException(new KXException("KException", e));
					e.printStackTrace();
				} catch (final IOException e) {
					if (KXConnectorAsyncImpl.this.reconnectOnError()) {
						KXConnectorAsyncImpl.this.throwKXError(new KXError("Could not read from " + KXConnectorAsyncImpl.this.getHost()
								+ ":" + KXConnectorAsyncImpl.this.getPort()));
						KXConnectorAsyncImpl.this.reconnect();
						break;
					} else {
						KXConnectorAsyncImpl.this.throwKXException(new KXException("Could not read from " + KXConnectorAsyncImpl.this.getHost() + ":"
								+ KXConnectorAsyncImpl.this.getPort(), e));
					}
				}
			}
			System.out.println("stop" + this);
		}
	}

}
