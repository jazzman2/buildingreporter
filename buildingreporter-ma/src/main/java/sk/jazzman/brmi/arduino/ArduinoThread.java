/**
 * 
 */
package sk.jazzman.brmi.arduino;

import java.lang.reflect.Constructor;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.measurement.MLogInf;

/**
 * @author jano
 * 
 */
public class ArduinoThread extends Thread {

	private final SandboxInf sandbox;
	private static final Logger logger = LoggerFactory.getLogger(ArduinoThread.class);

	/**
	 * {@link Constructor}
	 * 
	 * @param sandbox
	 */
	public ArduinoThread(SandboxInf sandbox) {
		if (sandbox == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		this.sandbox = sandbox;
	}

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	/**
	 * Getter {@link SandboxInf}
	 * 
	 * @return
	 */
	public SandboxInf getSandbox() {
		return sandbox;
	}

	@Override
	public void run() {
		super.run();

		while (true) {
			if (getSandbox().isInitialized()) {
				createMLog();
			}

			try {
				sleep(10000l);
			} catch (InterruptedException e) {
				getLogger().debug(" sleep error", e);
				return;
			}
		}
	}

	private void createMLog() {
		MLogInf log = new MLog();
		log.setLogDate(new java.sql.Time(System.currentTimeMillis()));
		log.setValueMeasured(Long.valueOf((new Random().nextLong() * 20l) - 10l));
		log.set
	}
}
