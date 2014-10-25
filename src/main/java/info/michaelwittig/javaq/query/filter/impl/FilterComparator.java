package info.michaelwittig.javaq.query.filter.impl;

import info.michaelwittig.javaq.Q;

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
		return this.q;
	}
	
}
