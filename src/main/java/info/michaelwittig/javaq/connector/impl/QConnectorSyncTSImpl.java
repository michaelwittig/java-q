package info.michaelwittig.javaq.connector.impl;

import info.michaelwittig.javaq.connector.QConnectorError;
import info.michaelwittig.javaq.connector.QConnectorSync;
import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommand;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommandFunction;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommandQ;
import info.michaelwittig.javaq.connector.impl.cmd.ConnectorSyncCommandSelect;
import info.michaelwittig.javaq.query.Function;
import info.michaelwittig.javaq.query.Result;
import info.michaelwittig.javaq.query.Select;

import java.io.IOException;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import kx.c.KException;

import com.google.common.util.concurrent.SettableFuture;

/**
 * Q Connector implementation.
 * 
 * Thread-safe? yes
 * 
 * @author mwittig
 * 
 */
final class QConnectorSyncTSImpl extends QConnectorImpl implements QConnectorSync {
	
	/** Stop command. */
	private final static ConnectorSyncCommandQ STOP_COMMAND = new ConnectorSyncCommandQ("");
	
	/** Start command. */
	private final static ConnectorSyncCommandQ START_COMMAND = new ConnectorSyncCommandQ("");
	
	/** Empty response. */
	private final static Result EMPTY_RESULT = new Result() {
		// nothing to do here
	};
	
	/** Commands. */
	private final BlockingQueue<ConnectorSyncCommandWithFutureValue> commands = new LinkedBlockingQueue<ConnectorSyncCommandWithFutureValue>();
	
	/** Executor. */
	private final AtomicReference<Executor> executor = new AtomicReference<Executor>(null);
	
	
	/**
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected QConnectorSyncTSImpl(String aHost, int aPort, boolean aReconnectOnError) {
		super(aHost, aPort, aReconnectOnError);
	}
	
	@Override
	public void connect() throws QConnectorException, QConnectorError {
		if (!this.executor.compareAndSet(null, new Executor())) {
			throw new QConnectorError("Already connected");
		}
		final SettableFuture<Result> future = SettableFuture.create();
		this.commands.offer(new ConnectorSyncCommandWithFutureValue(QConnectorSyncTSImpl.START_COMMAND, future));
		new Thread(this.executor.get()).start();
		try {
			future.get();
		} catch (final ExecutionException e) {
			throw new QConnectorException("KException", e);
		} catch (final InterruptedException e) {
			throw new QConnectorException("KException", e);
		}
	}
	
	@Override
	public void disconnect() throws QConnectorError {
		final Executor old = this.executor.get();
		if (old == null) {
			throw new QConnectorError("Not connected");
		}
		if (!this.executor.compareAndSet(old, null)) {
			throw new QConnectorError("Already disconnected");
		}
		final SettableFuture<Result> future = SettableFuture.create();
		this.commands.offer(new ConnectorSyncCommandWithFutureValue(QConnectorSyncTSImpl.STOP_COMMAND, future));
	}
	
	@Override
	public boolean isConnected() {
		if (this.executor.get() != null) {
			return true;
		}
		return false;
	}
	
	private Result cmd(final ConnectorSyncCommand cmd) throws QConnectorException {
		final SettableFuture<Result> future = SettableFuture.create();
		this.commands.offer(new ConnectorSyncCommandWithFutureValue(cmd, future));
		try {
			return future.get();
		} catch (final InterruptedException e) {
			throw new QConnectorException(e.getMessage());
		} catch (final ExecutionException e) {
			throw new QConnectorException(e.getMessage());
		}
	}
	
	@Override
	public Result execute(final String q) throws QConnectorException {
		return this.cmd(new ConnectorSyncCommandQ(q));
	}
	
	@Override
	public Result select(final Select select) throws QConnectorException {
		return this.cmd(new ConnectorSyncCommandSelect(select));
	}
	
	@Override
	public Result call(final Function function) throws QConnectorException {
		return this.cmd(new ConnectorSyncCommandFunction(function));
	}
	
	
	/**
	 * Executor.
	 * 
	 * @author mwittig
	 * 
	 */
	private final class Executor implements Runnable {
		
		@Override
		public void run() {
			c c = null;
			while (true) {
				final ConnectorSyncCommandWithFutureValue t;
				try {
					t = QConnectorSyncTSImpl.this.commands.take();
				} catch (final InterruptedException e) {
					continue;
				}
				
				final ConnectorSyncCommand cmd = t.getCmd();
				final SettableFuture<Result> future = t.getFuture();
				
				if (cmd == QConnectorSyncTSImpl.START_COMMAND) {
					if (c != null) {
						future.setException(new QConnectorError("Already connected"));
						continue;
					}
					
					try {
						c = new c(QConnectorSyncTSImpl.this.getHost(), QConnectorSyncTSImpl.this.getPort());
						c.tz = TimeZone.getTimeZone("UTC");
						future.set(QConnectorSyncTSImpl.EMPTY_RESULT);
					} catch (final KException e) {
						future.setException(e);
					} catch (final IOException e) {
						future.setException(e);
					}
				} else {
					if (c == null) {
						try {
							try {
								Thread.sleep(QConnectorImpl.RECONNECT_OFFSET_PER_TRY);
							} catch (final InterruptedException ie) {
								// suppress
							}
							c = new c(QConnectorSyncTSImpl.this.getHost(), QConnectorSyncTSImpl.this.getPort());
						} catch (final KException e) {
							future.setException(e);
							continue;
						} catch (final IOException e) {
							future.setException(e);
							continue;
						}
					}
					
					if (cmd == QConnectorSyncTSImpl.STOP_COMMAND) {
						try {
							c.close();
							c = null;
						} catch (final Exception e) {
							// suppress
						}
						future.set(QConnectorSyncTSImpl.EMPTY_RESULT);
						break;
					}
					
					try {
						final Result result = cmd.execute(c);
						future.set(result);
					} catch (final QConnectorException e) {
						future.setException(e);
					} catch (final KException e) {
						future.setException(new QConnectorException("Q failed", e));
					} catch (final IOException e) {
						if ((t.tryReconnect() == true) && QConnectorSyncTSImpl.this.reconnectOnError()) {
							c = null;
							QConnectorSyncTSImpl.this.commands.offer(t);
						} else {
							future.setException(new QConnectorException("Could not talk to " + QConnectorSyncTSImpl.this.getHost() + ":" + QConnectorSyncTSImpl.this.getPort(), e));
						}
					}
				}
			}
		}
	}
	
	/**
	 * @author mwittig
	 * 
	 *         Wraps a command with a FutureValue.
	 */
	private final static class ConnectorSyncCommandWithFutureValue {
		
		/** Command. */
		private final ConnectorSyncCommand cmd;
		
		/** Result future. */
		private final SettableFuture<Result> future;
		
		/** Indicates if the command is in the queue the 3nd time (reconnect). */
		private final AtomicBoolean reconnectTry = new AtomicBoolean(false);
		
		
		/**
		 * @param cmd Command
		 * @param future Result future
		 */
		public ConnectorSyncCommandWithFutureValue(final ConnectorSyncCommand cmd, final SettableFuture<Result> future) {
			super();
			this.cmd = cmd;
			this.future = future;
		}
		
		/**
		 * @return Command
		 */
		public ConnectorSyncCommand getCmd() {
			return this.cmd;
		}
		
		/**
		 * @return Result future
		 */
		public SettableFuture<Result> getFuture() {
			return this.future;
		}
		
		/**
		 * @return true if the command was not yet in reconnect state
		 */
		public boolean tryReconnect() {
			return this.reconnectTry.compareAndSet(false, true);
		}
		
	}
}
