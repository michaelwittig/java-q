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
import de.cinovo.q.connector.impl.KXResultHelper;
import de.cinovo.q.query.Result;

/**
 * Abstract kx synchronous command.
 * 
 * @author mwittig
 * 
 */
public abstract class AKXSyncCommand implements KXSyncCommand {
	
	/**
	 * @param c C
	 * @param q Q
	 * @return Result
	 * @throws KXException If something went wrong
	 * @throws KException If something went wrong
	 * @throws IOException If something went wrong
	 */
	protected final Result execute(final c c, final String q) throws KXException, KException, IOException {
		final Object res = c.k(q);
		final Result result = KXResultHelper.convert(res);
		if (result == null) {
			throw new KXException("Unsupported sync result type: " + res.getClass().getSimpleName());
		}
		return result;
	}
}
