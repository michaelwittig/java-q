// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.filter.impl;

import de.cinovo.q.Q;

/**
 * Filter comparator.
 *
 * @author mwittig
 *
 */
public enum FilterComparator implements Q {

	/** >. */
	greater(">"),

	/** >=. */
	greaterOrEqual(">="),

	/** <. */
	smaller("<"),

	/** <=. */
	smallerOrEqual("<="),

	/** =. */
	equal("="),

	/** <>. */
	notEqua("<>"),

	/** in. */
	in(" in ");

	/** Q. */
	private final String q;

	/**
	 * @param aQ Q
	 */
	private FilterComparator(final String aQ) {
		this.q = aQ;
	}

	@Override
	public String toQ() {
		return q;
	}

}
