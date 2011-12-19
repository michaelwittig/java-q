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

}
