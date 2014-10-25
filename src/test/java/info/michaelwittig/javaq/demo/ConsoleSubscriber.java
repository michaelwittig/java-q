package info.michaelwittig.javaq.demo;

import info.michaelwittig.javaq.connector.QConnectorAsync;
import info.michaelwittig.javaq.connector.QConnectorError;
import info.michaelwittig.javaq.connector.QConnectorException;
import info.michaelwittig.javaq.connector.QConnectorListener;
import info.michaelwittig.javaq.connector.impl.QConnectorFactory;
import info.michaelwittig.javaq.query.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console subscriber.
 * 
 * @author mwittig
 * 
 */
public final class ConsoleSubscriber implements QConnectorListener {
	
	/** Q connector. */
	private final QConnectorAsync c = QConnectorFactory.create(this, "localhost", 5010, true);
	
	
	/**
	 * @param args Arguments not needed
	 */
	public static void main(final String[] args) {
		try {
			final ConsoleSubscriber console = new ConsoleSubscriber();
			console.start();
			System.out.println("ConsoleSubscriber: Enter a command...");
			final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			while ((line = in.readLine()) != null) {
				System.out.println("Command: " + line);
				final String[] l = line.split(" ");
				if (l[0].equals("quit")) {
					break;
				} else if (l[0].equals("start")) {
					console.start();
				} else if (l[0].equals("stop")) {
					console.stop();
				} else if (l[0].equals("sub") || l[0].equals("subscribe")) {
					console.subscribe(l[1], l[2]);
				} else {
					System.err.println("Unknown command: " + line);
				}
			}
			in.close();
			console.stop();
			System.out.println("Good bye");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	/** */
	public ConsoleSubscriber() {
		super();
	}
	
	/**
	 * Start.
	 */
	public void start() {
		try {
			this.c.connect();
		} catch (final QConnectorException e) {
			System.err.println("QConnectorException: " + e);
		} catch (final QConnectorError e) {
			System.err.println("QConnectorError: " + e);
		}
	}
	
	/**
	 * Stop.
	 */
	public void stop() {
		try {
			this.c.disconnect();
		} catch (final QConnectorError e) {
			System.err.println("QConnectorError: " + e);
		}
	}
	
	/**
	 * Subscribe.
	 * 
	 * @param table Table
	 * @param symbol Symbol
	 */
	public void subscribe(final String table, final String symbol) {
		try {
			this.c.subscribe("", new String[] {table}, new String[] {symbol});
		} catch (final QConnectorException e) {
			System.err.println("QConnectorException: " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void error(final QConnectorError e) {
		System.out.println(e);
	}
	
	@Override
	public void exception(final QConnectorException e) {
		System.out.println(e);
		e.printStackTrace();
	}
	
	@Override
	public void resultReceived(String handle, Result result) {
		System.out.println(result);
	}
	
}
