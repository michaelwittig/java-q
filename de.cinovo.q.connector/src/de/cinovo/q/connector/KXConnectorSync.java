// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

import de.cinovo.q.query.Select;


/**
 * KXConnector synchronous.
 *
 * Thread-safe? no
 *
 * @author mwittig
 *
 */
public interface KXConnectorSync extends KXConnector {

	/**
	 * Synchronous query.
	 *
	 * @param cmd Command
	 * @return KX table
	 * @throws KXException If something went wrong
	 */
	KXTable query(String cmd) throws KXException;

	/**
	 * Synchronous select.
	 *
	 * @param select Select
	 * @return KX table
	 * @throws KXException If something went wrong
	 */
	KXTable select(Select select) throws KXException;

}
