// -------------------------------------------------------------------------------
// Copyright (c) 2011 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.connector;

/**
 * KXExceptiosn must be handled by yourself! They areindicatijng that the system is not in a proper state.
 *
 * @author mwittig
 *
 */
public final class KXException extends Exception {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message Message
	 * @param cause Cause
	 */
	public KXException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message Message
	 */
	public KXException(final String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "KXException: " + this.getMessage();
	}

}
