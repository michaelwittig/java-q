// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl.cmd;

import java.io.IOException;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.query.Function;
import de.cinovo.q.query.Result;

/**
 * KX function command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXSyncCommandFunction extends AKXSyncCommand {
	
	/** Function. */
	private final Function function;
	
	
	/**
	 * @param aFunction Function
	 */
	public KXSyncCommandFunction(final Function aFunction) {
		super();
		this.function = aFunction;
	}
	
	@Override
	public Result execute(final c c) throws KXException, KException, IOException {
		return this.execute(c, this.function.toQ());
	}
	
}
