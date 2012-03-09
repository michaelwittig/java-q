// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

import de.cinovo.q.query.Result;
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
	 * Synchronous select.
	 *
	 * @param select Select
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result select(Select select) throws KXException;

}
