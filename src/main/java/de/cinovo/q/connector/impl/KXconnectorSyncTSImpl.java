// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.io.IOException;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXConnectorSync;
import de.cinovo.q.connector.KXError;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.connector.impl.cmd.KXSyncCommand;
import de.cinovo.q.connector.impl.cmd.KXSyncCommandFunction;
import de.cinovo.q.connector.impl.cmd.KXSyncCommandQ;
import de.cinovo.q.connector.impl.cmd.KXSyncCommandSelect;
import de.cinovo.q.query.Function;
import de.cinovo.q.query.Result;
import de.cinovo.q.query.Select;

/**
 * KX Connector implementation.
 * 
 * Thread-safe? yes
 * 
 * @author mwittig
 * 
 */
final class KXconnectorSyncTSImpl extends KXConnectorImpl implements KXConnectorSync {
	
	/** Stop command. */
	private static final KXSyncCommandQ STOP_COMMAND = new KXSyncCommandQ("");
	
	/** Start command. */
	private static final KXSyncCommandQ START_COMMAND = new KXSyncCommandQ("");
	
	/** Commands. */
	private final BlockingQueue<KXSyncCommandWithFutureValue> commands = new LinkedBlockingQueue<KXSyncCommandWithFutureValue>();
	
	/** Executor. */
	private AtomicReference<Executor> executor = new AtomicReference<Executor>(null);
	
	
	/**
	 * @param aHost Host
	 * @param aPort Port
	 * @param aReconnectOnError Reconnect on error?
	 */
	protected KXconnectorSyncTSImpl(String aHost, int aPort, boolean aReconnectOnError) {
		super(aHost, aPort, aReconnectOnError);
	}
	
	@Override
	public void connect() throws KXException, KXError {
		if (!this.executor.compareAndSet(null, new Executor())) {
			throw new KXError("Already connected");
		}
		final FutureValue<Result> future = new FutureValue<Result>();
		this.commands.offer(new KXSyncCommandWithFutureValue(KXconnectorSyncTSImpl.START_COMMAND, future));
		new Thread(this.executor.get()).start();
		try {
			future.get();
		} catch (final ExecutionException e) {
			throw new KXException("KException", e);
		} catch (final InterruptedException e) {
			throw new KXException("KException", e);
		}
	}
	
	@Override
	public void disconnect() throws KXError {
		final Executor old = this.executor.get();
		if (old == null) {
			throw new KXError("Not connected");
		}
		if (!this.executor.compareAndSet(old, null)) {
			throw new KXError("Already disconnected");
		}
		this.commands.offer(new KXSyncCommandWithFutureValue(KXconnectorSyncTSImpl.STOP_COMMAND, new FutureValue<Result>()));
	}
	
	@Override
	public boolean isConnected() {
		if (this.executor.get() != null) {
			return true;
		}
		return false;
	}
	
	private Result cmd(final KXSyncCommand cmd) throws KXException {
		final FutureValue<Result> future = new FutureValue<Result>();
		this.commands.offer(new KXSyncCommandWithFutureValue(cmd, future));
		try {
			return future.get();
		} catch (final InterruptedException e) {
			throw new KXException(e.getMessage());
		} catch (final ExecutionException e) {
			throw new KXException(e.getMessage());
		}
	}
	
	@Override
	public Result execute(final String q) throws KXException {
		return this.cmd(new KXSyncCommandQ(q));
	}
	
	@Override
	public Result select(final Select select) throws KXException {
		return this.cmd(new KXSyncCommandSelect(select));
	}
	
	@Override
	public Result call(final Function function) throws KXException {
		return this.cmd(new KXSyncCommandFunction(function));
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
				final KXSyncCommandWithFutureValue t;
				try {
					t = KXconnectorSyncTSImpl.this.commands.take();
				} catch (final InterruptedException e) {
					continue;
				}
				
				final KXSyncCommand cmd = t.getCmd();
				final FutureValue<Result> future = t.getFuture();
				
				if (cmd == KXconnectorSyncTSImpl.START_COMMAND) {
					if (c != null) {
						future.error(new KXError("Already connected"));
						continue;
					}
					
					try {
						c = new c(KXconnectorSyncTSImpl.this.getHost(), KXconnectorSyncTSImpl.this.getPort());
						c.tz = TimeZone.getTimeZone("UTC");
						future.set(null);
					} catch (final KException e) {
						future.error(e);
					} catch (final IOException e) {
						future.error(e);
					}
				} else {
					if (c == null) {
						try {
							c = new c(KXconnectorSyncTSImpl.this.getHost(), KXconnectorSyncTSImpl.this.getPort());
						} catch (final KException e) {
							future.error(e);
							continue;
						} catch (final IOException e) {
							future.error(e);
							continue;
						}
					}
					
					if (cmd == KXconnectorSyncTSImpl.STOP_COMMAND) {
						try {
							c.close();
							c = null;
						} catch (final Exception e) {
							e.printStackTrace();
						}
						future.set(null);
						break;
					}
					
					try {
						final Result result = cmd.execute(c);
						future.set(result);
					} catch (final KXException e) {
						future.error(e);
					} catch (final KException e) {
						future.error(new KXException("Q failed", e));
					} catch (final IOException e) {
						if ((t.tryReconnect() == true) && KXconnectorSyncTSImpl.this.reconnectOnError()) {
							c = null;
							try {
								Thread.sleep(KXConnectorImpl.RECONNECT_OFFSET_PER_TRY);
							} catch (final InterruptedException ie) {
								ie.printStackTrace();
							}
							KXconnectorSyncTSImpl.this.commands.offer(t);
						} else {
							future.error(new KXException("Could not talk to " + KXconnectorSyncTSImpl.this.getHost() + ":" + KXconnectorSyncTSImpl.this.getPort(), e));
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * Copyright 2012 Cinovo AG
	 * 
	 * @author mwittig
	 * 
	 *         Wraps a command with a FutureValue.
	 */
	private final static class KXSyncCommandWithFutureValue {
		
		/** Command. */
		private final KXSyncCommand cmd;
		
		/** Result future. */
		private final FutureValue<Result> future;
		
		/** Indicates if the command is in the queue the 3nd time (reconnect). */
		private final AtomicBoolean reconnectTry = new AtomicBoolean(false);
		
		
		/**
		 * @param cmd Command
		 * @param future Result future
		 */
		public KXSyncCommandWithFutureValue(final KXSyncCommand cmd, final FutureValue<Result> future) {
			super();
			this.cmd = cmd;
			this.future = future;
		}
		
		/**
		 * @return Command
		 */
		public KXSyncCommand getCmd() {
			return this.cmd;
		}
		
		/**
		 * @return Result future
		 */
		public FutureValue<Result> getFuture() {
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
