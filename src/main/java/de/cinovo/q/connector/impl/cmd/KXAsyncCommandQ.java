// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl.cmd;

import java.io.IOException;

import kx.c;
import kx.c.KException;

/**
 * KX Q command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXAsyncCommandQ implements KXAsyncCommand {
	
	/** Q. */
	private final String q;
	
	
	/**
	 * @param aQ Q
	 */
	public KXAsyncCommandQ(final String aQ) {
		super();
		this.q = aQ;
	}
	
	@Override
	public void execute(final c c) throws KException, IOException {
		c.ks(this.q);
	}
	
}
