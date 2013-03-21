// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query;

/**
 * List result.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 */
public final class ListResult<J> implements Result {
	
	/** List. */
	private final J[] list;
	
	
	/**
	 * @param aList List
	 */
	public ListResult(final J[] aList) {
		this.list = aList;
	}
	
	/**
	 * @return List
	 */
	public J[] getList() {
		return this.list;
	}
	
}
