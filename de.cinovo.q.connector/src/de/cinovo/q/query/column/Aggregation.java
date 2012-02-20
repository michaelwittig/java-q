// -------------------------------------------------------------------------------
// Copyright (c) 2011-2012 Cinovo AG
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
// -------------------------------------------------------------------------------

package de.cinovo.q.query.column;

import de.cinovo.q.Q;

/**
 * Aggregations.
 *
 * @author mwittig
 *
 */
public enum Aggregation implements Q {

	/** Minimum. */
	min("min"),

	/** maximum. */
	max("max"),

	/** First element. */
	first("first"),

	/** Last element. */
	last("last"),

	/** Avg.*/
	avg("avg"),

	/** Sum.*/
	sum("sum"),

	/** Count. */
	count("count");

	/** Q. */
	private final String q;

	/**
	 * @param aQ Q
	 */
	private Aggregation(final String aQ) {
		this.q = aQ;
	}

	@Override
	public String toQ() {
		return this.q;
	}

}
