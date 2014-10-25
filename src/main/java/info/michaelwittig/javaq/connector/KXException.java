package info.michaelwittig.javaq.connector;

/**
 * KXExceptiosn must be handled by yourself! They are indicating that the system is not in a proper state any longer.
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
