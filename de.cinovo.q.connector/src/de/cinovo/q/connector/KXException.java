package de.cinovo.q.connector;

/**
 * KXExceptiosn must be handled by yourself! They areindicatijng that the system is not in a proper state.
 *
 * @author mwittig
 *
 */
public class KXException extends Exception {

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

}
