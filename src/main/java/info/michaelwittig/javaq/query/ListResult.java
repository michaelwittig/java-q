package info.michaelwittig.javaq.query;

/**
 * List result.
 * 
 * @author mwittig
 * 
 * @param <J> Java type
 */
public final class ListResult<J> implements Result {
	
	/** List. */
	private final J[] list;
	
	
	/**
	 * @param aList List
	 */
	public ListResult(final J[] aList) {
		this.list = aList;
	}
	
	/**
	 * @return List
	 */
	public J[] getList() {
		return this.list;
	}
	
}
