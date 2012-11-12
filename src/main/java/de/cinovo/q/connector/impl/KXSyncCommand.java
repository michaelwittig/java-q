// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXException;
import de.cinovo.q.query.Result;

/**
 * KX command with result.
 * 
 * @author mwittig
 * 
 */
interface KXSyncCommand {
	
	/**
	 * Execute the command via c.
	 * 
	 * @param c C
	 * @return Result
	 * @throws KXException If something went wrong
	 * @throws KException If something went wrong in q
	 * @throws IOException If something went wrong on the transport layer
	 */
	Result execute(final c c) throws KXException, KException, IOException;
	
}
