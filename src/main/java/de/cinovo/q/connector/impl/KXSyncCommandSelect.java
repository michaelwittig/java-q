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
import de.cinovo.q.query.Select;

/**
 * KX string command with result.
 * 
 * @author mwittig
 * 
 */
public final class KXSyncCommandSelect extends AKXSyncCommand {

	/** Select. */
	private final Select select;

	/**
	 * @param aSelect
	 *            Select
	 */
	public KXSyncCommandSelect(final Select aSelect) {
		super();
		this.select = aSelect;
	}

	/**
	 * @return the select
	 */
	public Select getSelect() {
		return this.select;
	}

	@Override
	public Result execute(final c c) throws KXException, KException, IOException {
		return this.execute(c, this.select.toQ());
	}

}
