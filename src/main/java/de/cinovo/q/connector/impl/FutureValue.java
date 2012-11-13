package de.cinovo.q.connector.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * Copyright 2012 Cinovo AG
 * 
 * @author mwittig
 * 
 * @param <V> Value
 */
public final class FutureValue<V> implements Future<V> {
	
	/** Value. */
	private volatile V value;
	
	/** Exception. */
	private volatile Exception exception;
	
	/** Latch. */
	private final CountDownLatch latch = new CountDownLatch(1);
	
	
	/**
	 * @param aValue Value
	 */
	public void set(final V aValue) {
		this.value = aValue;
		this.exception = null;
		this.latch.countDown();
	}
	
	/**
	 * @param anException Exception
	 */
	public void error(final Exception anException) {
		this.value = null;
		this.exception = anException;
		this.latch.countDown();
	}
	
	@Override
	public boolean cancel(final boolean mayInterruptIfRunning) {
		throw new UnsupportedOperationException();
	}
	
	private V result() throws ExecutionException {
		if (this.exception != null) {
			throw new ExecutionException(this.exception);
		}
		return this.value;
	}
	
	@Override
	public V get() throws InterruptedException, ExecutionException {
		this.latch.await();
		return this.result();
	}
	
	@Override
	public V get(final long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		this.latch.await(timeout, unit);
		return this.result();
	}
	
	@Override
	public boolean isCancelled() {
		return false;
	}
	
	@Override
	public boolean isDone() {
		return (this.latch.getCount() == 0);
	}
	
}
