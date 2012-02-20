// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector.impl;

import java.io.IOException;

import kx.c;
import kx.c.KException;
import de.cinovo.q.connector.KXException;

/**
 * KX command without result.
 *
 * @author mwittig
 *
 */
interface KXAsyncCommand {

	/**
	 * Execute the command via c.
	 *
	 * @param c C
	 * @throws KXException If something went wrong
	 * @throws KException If something went wrong in q
	 * @throws IOException If something went wrong on the transport layer
	 */
	void execute(final c c) throws KXException, KException, IOException;

}
