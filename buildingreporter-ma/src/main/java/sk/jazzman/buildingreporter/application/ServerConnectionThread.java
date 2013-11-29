/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.server.ws.action.RegisterMeasureInstrumnet;

/**
 * @author jkovalci
 * 
 */
public class ServerConnectionThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(ServerConnectionThread.class);

	private final MeasureInstrumentApplication appl;

	/**
	 * {@link Constructor}
	 * 
	 */
	public ServerConnectionThread(MeasureInstrumentApplication appl) {
		this.appl = appl;
	}

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	private static Logger getLogger() {
		return log;
	}

	@Override
	public void run() {
		super.run();

		if (appl.isInitialized()) {

			for (int index = 1; index < 100; index++) {
				check();

				try {
					sleep(10000l);
				} catch (InterruptedException e) {
					getLogger().debug(" sleep error", e);
					return;
				}
			}
		} else {
			getLogger().error("Not initialyzed yet.");

			try {
				sleep(10000l);
			} catch (InterruptedException e) {
				getLogger().debug(" sleep error", e);
				return;
			}
		}

	}

	/**
	 * Ping server
	 */
	protected void check() {
		try {

			getLogger().debug(" ping server ...");
			appl.getServerActionHandler().perform("/register", new RegisterMeasureInstrumnet.ParamBuilder().setConfiguration(appl.getConfiguration()).build());
			getLogger().debug(" ping server done ...");

			getLogger().debug(" try load data from db");
			appl.getJpaActionHandler().perform("GET-mlog", new HashMap<String, Object>());
		} catch (Exception e) {
			getLogger().error("ping server failed", e);
		}
	}
}
