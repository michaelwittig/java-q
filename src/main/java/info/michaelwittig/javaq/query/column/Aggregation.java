package info.michaelwittig.javaq.query.column;

import info.michaelwittig.javaq.Q;

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
	
	/** Avg. */
	avg("avg"),
	
	/** Sum. */
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
