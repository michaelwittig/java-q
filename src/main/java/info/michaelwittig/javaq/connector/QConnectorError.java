package info.michaelwittig.javaq.connector;

/**
 * QConnectorErrors are handled by the QConnector. They are just an information. Nothing needs to be done!
 * 
 * @author mwittig
 * 
 */
public final class QConnectorError extends Exception {
	
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param message Message
	 */
	public QConnectorError(final String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return "QConnectorError: " + this.getMessage();
	}
	
}
