// -------------------------------------------------------------------------------
// Copyright (c) 2011 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

/**
 * KXErrors are handled by the KXConnector. They are just an information. Nothing needs to be done!
 *
 * @author mwittig
 *
 */
public final class KXError extends Exception {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message Message
	 */
	public KXError(final String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "KXError: " + this.getMessage();
	}

}
