// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query;

/**
 * Primitive result.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 */
public final class PrimitiveResult<J> implements Result {
	
	/** Primitive. */
	private final J primitive;
	
	
	/**
	 * @param aPrimitive Primitive
	 */
	public PrimitiveResult(final J aPrimitive) {
		this.primitive = aPrimitive;
	}
	
	/**
	 * @return Primitive
	 */
	public J getPrimitive() {
		return this.primitive;
	}
	
}
