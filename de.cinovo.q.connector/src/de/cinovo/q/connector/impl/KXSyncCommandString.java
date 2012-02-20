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
import de.cinovo.q.connector.KXTable;

/**
 * KX string command with result.
 *
 * @author mwittig
 *
 */
public final class KXSyncCommandString implements KXSyncCommand {

	/** Command. */
	private final String cmd;

	/**
	 * @param aCmd Command
	 */
	public KXSyncCommandString(final String aCmd) {
		super();
		this.cmd = aCmd;
	}

	public String getCmd() {
		return cmd;
	}

	@Override
	public KXTable execute(final c c) throws KXException, KException, IOException {
		final Object res = c.k(this.cmd);
		if (res instanceof c.Flip) {
			return new KXTableImpl((c.Flip) res);
		}
		throw new KXException("Result is not a table");
	}

}
