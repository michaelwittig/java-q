// -------------------------------------------------------------------------------
// Copyright (c) 2011-2013 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Apache License, Version 2.0
// which accompanies this distribution, and is available at
// http://www.apache.org/licenses/LICENSE-2.0.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

import de.cinovo.q.query.Result;

/**
 * KX listener.
 * 
 * @author mwittig
 * 
 */
public interface KXListener {
	
	/**
	 * KXError occurred. Is handled by the KXConnector. Just an information.
	 * 
	 * @param e Error
	 */
	void error(KXError e);
	
	/**
	 * KXException occurred. Must be handled by yourself!
	 * 
	 * @param e Exception
	 */
	void exception(KXException e);
	
	/**
	 * Result received.
	 * 
	 * @param handle Unique handle to identify received data
	 * @param result Result
	 */
	void resultReceived(final String handle, Result result);
	
}
