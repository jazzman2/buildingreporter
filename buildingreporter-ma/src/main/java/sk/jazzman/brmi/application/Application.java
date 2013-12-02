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

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	private static final Logger getLogger() {
		return log;
	}

	public static void main(String[] args) {
		Sandbox sandbox = new Sandbox();

		try {
			sandbox.init();
		} catch (Exception ex) {
			getLogger().error("Error during init application: ", ex);
		}

		ServerConnectionThread t = new ServerConnectionThread(sandbox);
		t.start();

		ArduinoThread at = new ArduinoThread(sandbox);
		at.start();
	}
}
