package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.KXConnectorAsync;
import info.michaelwittig.javaq.connector.KXDataListener;
import info.michaelwittig.javaq.connector.KXError;
import info.michaelwittig.javaq.connector.KXException;
import info.michaelwittig.javaq.connector.KXListener;
import info.michaelwittig.javaq.connector.impl.cmd.KXAsyncCommand;
import info.michaelwittig.javaq.connector.impl.cmd.KXAsyncCommandQ;
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
 * KX Connector implementation.
 * 
 * @author mwittig
 * 
 */
final class KXConnectorAsyncImpl extends KXConnectorImpl implements KXConnectorAsync {
	
	/** Stop command. */
	private static final KXAsyncCommandQ STOP_COMMAND = new KXAsyncCommandQ("");
	
	/** Listener. */
	private final KXListener listener;
	
	/** Connection. */
	private final AtomicReference<c> cref = new AtomicReference<c>();
	
	/** Callback executor service. */
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	/** Commands. */
	private final BlockingQueue<KXAsyncCommand> commands = new LinkedBlockingQueue<KXAsyncCommand>();
	
	/** Timer. */
	private final Timer timer = new Timer();
	
	/** Subscriptions. */
	private final CopyOnWriteArrayList<KXAsyncCommandQ> subscribes = new CopyOnWriteArrayList<KXAsyncCommandQ>();
	
	/** Current run. */
	private final AtomicReference<UUID> currentRun = new AtomicReference<UUID>();
	
	
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
		final UUID run = UUID.randomUUID();
		this.currentRun.set(run);
		try {
			if (!this.cref.compareAndSet(null, new c(this.getHost(), this.getPort()))) {
				throw new KXError("Already connected");
			}
			this.cref.get().tz = TimeZone.getTimeZone("UTC");
			new Thread(new Reader(run)).start();
			new Thread(new Executor(run, this.cref.get())).start();
		} catch (final KException e) {
			throw new KXException("KException", e);
		} catch (final IOException e) {
			if (this.reconnectOnError()) {
				this.throwKXError(new KXError("Could not connect to " + this.getHost() + ":" + this.getPort()));
				this.reconnect(run);
			} else {
				throw new KXException("Could not connect to " + this.getHost() + ":" + this.getPort(), e);
			}
		}
	}
	
	@Override
	public void disconnect() throws KXError {
		this.disconnect(true);
	}
	
	private void disconnect(final boolean clearSubscriptions) throws KXError {
		final c old = this.cref.get();
		if (old == null) {
			throw new KXError("Not connected");
		}
		if (!this.cref.compareAndSet(old, null)) {
			throw new KXError("Already disconnected");
		}
		if (clearSubscriptions == true) {
			this.subscribes.clear();
		}
		this.commands.offer(KXConnectorAsyncImpl.STOP_COMMAND);
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
			} catch (final KXError e) {
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
			} catch (final KXError e) {
				// suppress, because this just happens if the connection is not established
			}
			final long time = System.currentTimeMillis();
			this.timer.schedule(new ReconnectTask(), new Date(time + (KXConnectorImpl.RECONNECT_OFFSET_PER_TRY)));
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
		final KXAsyncCommandQ q = new KXAsyncCommandQ(".u.sub[" + t.toString() + ";" + s.toString() + "]");
		this.subscribes.add(q);
		this.execute(q);
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
	public KXListener getConnectorListener() {
		return this.listener;
	}
	
	/**
	 * Write to kx.
	 * 
	 * @param cmd Command
	 */
	private void execute(final KXAsyncCommand cmd) {
		this.commands.offer(cmd);
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
	 * Throw result.
	 * 
	 * @param result Result
	 */
	private void throwResult(final Result result) {
		this.executor.execute(new Runnable() {
			
			@Override
			public void run() {
				KXConnectorAsyncImpl.this.listener.resultReceived("", result);
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
				KXConnectorAsyncImpl.this.connect();
				for (final KXAsyncCommandQ q : KXConnectorAsyncImpl.this.subscribes) {
					KXConnectorAsyncImpl.this.execute(q);
				}
			} catch (final KXException e) {
				KXConnectorAsyncImpl.this.throwKXError(new KXError("Reconnect failed"));
			} catch (final KXError e) {
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
				final KXAsyncCommand cmd;
				try {
					cmd = KXConnectorAsyncImpl.this.commands.poll(1, TimeUnit.SECONDS);
				} catch (final InterruptedException e) {
					continue;
				}
				if (cmd == null) { // we do not received an command within 1 second
					try {
						this.c.ks("1"); // ping server
					} catch (final Exception e) {
						if (KXConnectorAsyncImpl.this.reconnectOnError()) {
							KXConnectorAsyncImpl.this.reconnect(this.run);
							KXConnectorAsyncImpl.this.throwKXError(new KXError("Could not talk to " + KXConnectorAsyncImpl.this.getHost() + ":" + KXConnectorAsyncImpl.this.getPort()));
						} else {
							KXConnectorAsyncImpl.this.disconnectSilent(this.run, true);
							KXConnectorAsyncImpl.this.throwKXException(new KXException("Could not talk to " + KXConnectorAsyncImpl.this.getHost() + ":" + KXConnectorAsyncImpl.this.getPort(), e));
						}
						break;
					}
					continue;
				}
				if (cmd == KXConnectorAsyncImpl.STOP_COMMAND) {
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
						KXConnectorAsyncImpl.this.reconnect(this.run);
						KXConnectorAsyncImpl.this.throwKXError(new KXError("Could not talk to " + KXConnectorAsyncImpl.this.getHost() + ":" + KXConnectorAsyncImpl.this.getPort()));
					} else {
						KXConnectorAsyncImpl.this.disconnectSilent(this.run, true);
						KXConnectorAsyncImpl.this.throwKXException(new KXException("Could not talk to " + KXConnectorAsyncImpl.this.getHost() + ":" + KXConnectorAsyncImpl.this.getPort(), e));
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
			while (KXConnectorAsyncImpl.this.cref.get() != null) {
				try {
					final Object res = KXConnectorAsyncImpl.this.cref.get().k();
					if (res == null) {
						// nothing to do here
						continue;
					}
					final Result result;
					try {
						result = KXResultHelper.convert(res);
					} catch (final KXException e) {
						KXConnectorAsyncImpl.this.throwKXException(e);
						continue;
					}
					KXConnectorAsyncImpl.this.throwResult(result);
				} catch (final SocketTimeoutException e) {
					continue;
				} catch (final UnsupportedEncodingException e) {
					KXConnectorAsyncImpl.this.throwKXException(new KXException("UnsupportedEncodingException", e));
				} catch (final KException e) {
					KXConnectorAsyncImpl.this.throwKXException(new KXException("KException", e));
				} catch (final IOException e) {
					if (KXConnectorAsyncImpl.this.reconnectOnError()) {
						KXConnectorAsyncImpl.this.reconnect(this.run);
						KXConnectorAsyncImpl.this.throwKXError(new KXError("Could not read from " + KXConnectorAsyncImpl.this.getHost() + ":" + KXConnectorAsyncImpl.this.getPort()));
					} else {
						KXConnectorAsyncImpl.this.disconnectSilent(this.run, true);
						KXConnectorAsyncImpl.this.throwKXException(new KXException("Could not read from " + KXConnectorAsyncImpl.this.getHost() + ":" + KXConnectorAsyncImpl.this.getPort(), e));
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
