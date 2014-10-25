package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.QConnectorAsync;
import info.michaelwittig.javaq.connector.QConnectorDataListener;
import info.michaelwittig.javaq.connector.QConnectorError;
import info.michaelwittig.javaq.connector.QConnectorListener;
import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorAsyncCommand;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorAsyncCommandQ;
import info.michaelwittig.javaq.query.Result;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import kx.c.KException;

/**
 * Q Connector implementation.
 * 
 * @author mwittig
 * 
 */
final class QConnectorAsyncImpl extends QConnectorImpl implements QConnectorAsync {
	
	/** Stop command. */
	private static final ConnectorAsyncCommandQ STOP_COMMAND = new ConnectorAsyncCommandQ("");
	
	/** Listener. */
	private final QConnectorListener listener;
	
	/** Connection. */
	private final AtomicReference<c> cref = new AtomicReference<c>();
	
	/** Callback executor service. */
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	/** Commands. */
	private final BlockingQueue<ConnectorAsyncCommand> commands = new LinkedBlockingQueue<ConnectorAsyncCommand>();
	
	/** Timer. */
	private final Timer timer = new Timer();
	
	/** Subscriptions. */
	private final CopyOnWriteArrayList<ConnectorAsyncCommandQ> subscribes = new CopyOnWriteArrayList<ConnectorAsyncCommandQ>();
	
	/** Current run. */
	private final AtomicReference<UUID> currentRun = new AtomicReference<UUID>();
	
	
	/**
	 * @param aListener Listener
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected QConnectorAsyncImpl(final QConnectorListener aListener, final String aHost, final int aPort, final boolean aReconnectOnError) {
		super(aHost, aPort, aReconnectOnError);
		this.listener = aListener;
	}
	
	@Override
	public void connect() throws QConnectorException, QConnectorError {
		final UUID run = UUID.randomUUID();
		this.currentRun.set(run);
		try {
			if (!this.cref.compareAndSet(null, new c(this.getHost(), this.getPort()))) {
				throw new QConnectorError("Already connected");
			}
			this.cref.get().tz = TimeZone.getTimeZone("UTC");
			new Thread(new Reader(run)).start();
			new Thread(new Executor(run, this.cref.get())).start();
		} catch (final KException e) {
			throw new QConnectorException("KException", e);
		} catch (final IOException e) {
			if (this.reconnectOnError()) {
				this.throwQError(new QConnectorError("Could not connect to " + this.getHost() + ":" + this.getPort()));
				this.reconnect(run);
			} else {
				throw new QConnectorException("Could not connect to " + this.getHost() + ":" + this.getPort(), e);
			}
		}
	}
	
	@Override
	public void disconnect() throws QConnectorError {
		this.disconnect(true);
	}
	
	private void disconnect(final boolean clearSubscriptions) throws QConnectorError {
		final c old = this.cref.get();
		if (old == null) {
			throw new QConnectorError("Not connected");
		}
		if (!this.cref.compareAndSet(old, null)) {
			throw new QConnectorError("Already disconnected");
		}
		if (clearSubscriptions == true) {
			this.subscribes.clear();
		}
		this.commands.offer(QConnectorAsyncImpl.STOP_COMMAND);
		try {
			old.close();
		} catch (final IOException e) {
			// suppress
		}
	}
	
	private void disconnectSilent(final UUID run, final boolean clearSubscriptions) {
		if (this.currentRun.compareAndSet(run, null) == true) {
			try {
				this.disconnect(clearSubscriptions);
			} catch (final QConnectorError e) {
				// suppress, because this just happens if the connection is not established
			}
		}
	}
	
	/**
	 * Reconnect.
	 */
	private void reconnect(final UUID run) {
		if (this.currentRun.compareAndSet(run, null) == true) {
			
			try {
				this.disconnect(false);
			} catch (final QConnectorError e) {
				// suppress, because this just happens if the connection is not established
			}
			final long time = System.currentTimeMillis();
			this.timer.schedule(new ReconnectTask(), new Date(time + (QConnectorImpl.RECONNECT_OFFSET_PER_TRY)));
		}
	}
	
	@Override
	public void subscribe(final QConnectorDataListener aListener, final String[] tables, final String[] symbols) throws QConnectorException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void subscribe(final String handle, final String[] tables, final String[] symbols) throws QConnectorException {
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
		final ConnectorAsyncCommandQ q = new ConnectorAsyncCommandQ(".u.sub[" + t.toString() + ";" + s.toString() + "]");
		this.subscribes.add(q);
		this.execute(q);
	}
	
	@Override
	public void unsubscribe(final String handle) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void unsubscribe(final QConnectorDataListener aListener) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public QConnectorListener getConnectorListener() {
		return this.listener;
	}
	
	/**
	 * Write to kx.
	 * 
	 * @param cmd Command
	 */
	private void execute(final ConnectorAsyncCommand cmd) {
		this.commands.offer(cmd);
	}
	
	/**
	 * Throw an exception if something happened, that the system can not fix.
	 * 
	 * @param e Exception
	 */
	private void throwQException(final QConnectorException e) {
		this.executor.execute(new Runnable() {
			
			@Override
			public void run() {
				QConnectorAsyncImpl.this.listener.exception(e);
			}
		});
	}
	
	/**
	 * Throw an error if something happened, that the system can fix on his own, but is might important to know.
	 * 
	 * @param e Error
	 */
	private void throwQError(final QConnectorError e) {
		this.executor.execute(new Runnable() {
			
			@Override
			public void run() {
				QConnectorAsyncImpl.this.listener.error(e);
			}
		});
	}
	
	/**
	 * Throw result.
	 * 
	 * @param result Result
	 */
	private void throwResult(final Result result) {
		this.executor.execute(new Runnable() {
			
			@Override
			public void run() {
				QConnectorAsyncImpl.this.listener.resultReceived("", result);
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
		
		/** */
		public ReconnectTask() {
			super();
		}
		
		@Override
		public void run() {
			try {
				QConnectorAsyncImpl.this.connect();
				for (final ConnectorAsyncCommandQ q : QConnectorAsyncImpl.this.subscribes) {
					QConnectorAsyncImpl.this.execute(q);
				}
			} catch (final QConnectorException e) {
				QConnectorAsyncImpl.this.throwQError(new QConnectorError("Reconnect failed"));
			} catch (final QConnectorError e) {
				// suppress, because this just happens if the connection is already established
			}
		}
	}
	
	/**
	 * Executor.
	 * 
	 * @author mwittig
	 */
	private final class Executor implements Runnable {
		
		/** Run. */
		private final UUID run;
		
		/** C. */
		private final c c;
		
		
		/**
		 * @param aRun Run
		 * @param aC C
		 */
		public Executor(final UUID aRun, final c aC) {
			this.run = aRun;
			this.c = aC;
		}
		
		@Override
		public void run() {
			while (true) {
				final ConnectorAsyncCommand cmd;
				try {
					cmd = QConnectorAsyncImpl.this.commands.poll(1, TimeUnit.SECONDS);
				} catch (final InterruptedException e) {
					continue;
				}
				if (cmd == null) { // we do not received an command within 1 second
					try {
						this.c.ks("1"); // ping server
					} catch (final Exception e) {
						if (QConnectorAsyncImpl.this.reconnectOnError()) {
							QConnectorAsyncImpl.this.reconnect(this.run);
							QConnectorAsyncImpl.this.throwQError(new QConnectorError("Could not talk to " + QConnectorAsyncImpl.this.getHost() + ":" + QConnectorAsyncImpl.this.getPort()));
						} else {
							QConnectorAsyncImpl.this.disconnectSilent(this.run, true);
							QConnectorAsyncImpl.this.throwQException(new QConnectorException("Could not talk to " + QConnectorAsyncImpl.this.getHost() + ":" + QConnectorAsyncImpl.this.getPort(), e));
						}
						break;
					}
					continue;
				}
				if (cmd == QConnectorAsyncImpl.STOP_COMMAND) {
					break;
				}
				try {
					cmd.execute(this.c);
				} catch (final QConnectorException e) {
					QConnectorAsyncImpl.this.throwQException(e);
				} catch (final KException e) {
					QConnectorAsyncImpl.this.throwQException(new QConnectorException("KException", e));
				} catch (final IOException e) {
					if (QConnectorAsyncImpl.this.reconnectOnError()) {
						QConnectorAsyncImpl.this.commands.offer(cmd);
						QConnectorAsyncImpl.this.reconnect(this.run);
						QConnectorAsyncImpl.this.throwQError(new QConnectorError("Could not talk to " + QConnectorAsyncImpl.this.getHost() + ":" + QConnectorAsyncImpl.this.getPort()));
					} else {
						QConnectorAsyncImpl.this.disconnectSilent(this.run, true);
						QConnectorAsyncImpl.this.throwQException(new QConnectorException("Could not talk to " + QConnectorAsyncImpl.this.getHost() + ":" + QConnectorAsyncImpl.this.getPort(), e));
					}
					break;
				}
			}
		}
	}
	
	/**
	 * Reader.
	 * 
	 * @author mwittig
	 * 
	 */
	private final class Reader implements Runnable {
		
		/** Run. */
		private final UUID run;
		
		
		/**
		 * @param aRun Run
		 */
		public Reader(final UUID aRun) {
			super();
			this.run = aRun;
		}
		
		@Override
		public void run() {
			while (QConnectorAsyncImpl.this.cref.get() != null) {
				try {
					final Object res = QConnectorAsyncImpl.this.cref.get().k();
					if (res == null) {
						// nothing to do here
						continue;
					}
					final Result result;
					try {
						result = CResultHelper.convert(res);
					} catch (final QConnectorException e) {
						QConnectorAsyncImpl.this.throwQException(e);
						continue;
					}
					QConnectorAsyncImpl.this.throwResult(result);
				} catch (final SocketTimeoutException e) {
					continue;
				} catch (final UnsupportedEncodingException e) {
					QConnectorAsyncImpl.this.throwQException(new QConnectorException("UnsupportedEncodingException", e));
				} catch (final KException e) {
					QConnectorAsyncImpl.this.throwQException(new QConnectorException("KException", e));
				} catch (final IOException e) {
					if (QConnectorAsyncImpl.this.reconnectOnError()) {
						QConnectorAsyncImpl.this.reconnect(this.run);
						QConnectorAsyncImpl.this.throwQError(new QConnectorError("Could not read from " + QConnectorAsyncImpl.this.getHost() + ":" + QConnectorAsyncImpl.this.getPort()));
					} else {
						QConnectorAsyncImpl.this.disconnectSilent(this.run, true);
						QConnectorAsyncImpl.this.throwQException(new QConnectorException("Could not read from " + QConnectorAsyncImpl.this.getHost() + ":" + QConnectorAsyncImpl.this.getPort(), e));
					}
					break;
				}
			}
		}
	}
	
	
	@Override
	public boolean isConnected() {
		if (this.cref.get() != null) {
			return true;
		}
		return false;
	}
	
}
