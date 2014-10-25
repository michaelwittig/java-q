package info.michaelwittig.javaq.connector;

/**
 * QConnectorExceptions must be handled by yourself! They are indicating that the system is not in a proper state any longer.
 * 
 * @author mwittig
 * 
 */
public final class QConnectorException extends Exception {
	
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param message Message
	 * @param cause Cause
	 */
	public QConnectorException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message Message
	 */
	public QConnectorException(final String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return "QConnectorException: " + this.getMessage();
	}
	
}
