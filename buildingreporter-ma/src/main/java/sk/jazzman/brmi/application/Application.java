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

		Application a = new Application();

		ServerConnectionThread st = new ServerConnectionThread(sandbox);
		ArduinoThread at = new ArduinoThread(sandbox);

		st.start();
		at.start();
	}
}
