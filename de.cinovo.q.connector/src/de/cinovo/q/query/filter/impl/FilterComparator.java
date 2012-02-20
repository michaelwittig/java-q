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
	notEqua("<>");

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
