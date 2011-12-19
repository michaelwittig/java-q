package de.cinovo.q.connector;

/**
 * KX data listener.
 *
 * @author mwittig
 *
 */
public interface KXDataListener {

	/**
	 * Data received.
	 *
	 * @param data KX table
	 */
	void dataReceived(KXTable data);
}
