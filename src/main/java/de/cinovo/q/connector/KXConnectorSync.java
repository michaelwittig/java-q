// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

import de.cinovo.q.query.Function;
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
	 * Synchronous execute.
	 * 
	 * @param q Q code
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result execute(String q) throws KXException;
	
	/**
	 * Synchronous select.
	 * 
	 * @param select Select
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result select(Select select) throws KXException;
	
	/**
	 * Synchronous call.
	 * 
	 * @param function Function
	 * @return Result
	 * @throws KXException If something went wrong
	 */
	Result call(Function function) throws KXException;
	
}
