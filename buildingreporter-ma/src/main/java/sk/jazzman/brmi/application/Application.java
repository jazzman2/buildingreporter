/**
 * 
 */
package sk.jazzman.brmi.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.arduino.ArduinoThread;

/**
 * @author jano
 * 
 */
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private boolean hasServerConnection = false;

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	private static final Logger getLogger() {
		return log;
	}

	/**
	 * ? true if has server connection
	 * 
	 * @return
	 */
	public synchronized boolean hasServerConnection() {
		return hasServerConnection;
	}

	/**
	 * Set value hasServerConnection
	 * 
	 * @param hasServerConnection
	 */
	public synchronized void setServerConnection(boolean hasServerConnection) {
		this.hasServerConnection = hasServerConnection;
	}

	public static void main(String[] args) {
		Sandbox sandbox = new Sandbox();

		try {
			sandbox.init();
		} catch (Exception ex) {
			getLogger().error("Error during init application: ", ex);
		}

		Application a = new Application();

		ServerConnectionThread t = new ServerConnectionThread(a, sandbox);
		t.start();

		ArduinoThread at = new ArduinoThread(sandbox);
		at.start();
	}
}
