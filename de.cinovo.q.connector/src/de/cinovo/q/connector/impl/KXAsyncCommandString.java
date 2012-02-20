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

/**
 * KX string command without result.
 *
 * @author mwittig
 *
 */
final class KXAsyncCommandString implements KXAsyncCommand {

	/** Command. */
	private final String cmd;

	/**
	 * @param aCmd Command
	 */
	public KXAsyncCommandString(final String aCmd) {
		super();
		this.cmd = aCmd;
	}

	public String getCmd() {
		return cmd;
	}

	@Override
	public void execute(final c c) throws KException, IOException {
		c.ks(this.cmd);
	}

}
