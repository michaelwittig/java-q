package de.cinovo.q.query;

/**
 * Result wrapper.
 *
 * @author mwittig
 *
 */
public interface ResultWrapper {

	/**
	 * @param res Result
	 * @return Wrapped result
	 */
	ResultWrapper fromQ(Object res);

}
