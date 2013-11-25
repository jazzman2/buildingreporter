/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		MeasureInstrumentApplication appl = new MeasureInstrumentApplication();

		try {
			appl.init();
		} catch (Exception ex) {
			getLogger().error("Error during init application: ", ex);
		}

		// Thread t = new Thread() {
		// @Override
		// public void run() {
		// // the following line will keep this app alive for 1000 seconds,
		// // waiting for events to occur and responding to them (printing
		// // incoming messages to console).
		// try {
		// Thread.sleep(1000000);
		// } catch (InterruptedException ie) {
		// }
		// }
		// };
		// t.start();
		// System.out.println("Started");

		ServerConnectionThread t = new ServerConnectionThread();
		t.setServerActionHandler(appl.getServerActionHandler());
		t.setConfiguration(appl.getConfiguration());
		t.start();
	}
}
