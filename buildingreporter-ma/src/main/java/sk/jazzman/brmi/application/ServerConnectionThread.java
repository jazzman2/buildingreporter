/**
 * 
 */
package sk.jazzman.brmi.application;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.server.ws.action.RegisterMeasureInstrumnet;

/**
 * @author jkovalci
 * 
 */
public class ServerConnectionThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(ServerConnectionThread.class);

	private final Sandbox sandbox;

	/**
	 * {@link Constructor}
	 * 
	 */
	public ServerConnectionThread(Sandbox sandbox) {
		this.sandbox = sandbox;
	}

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	private static Logger getLogger() {
		return log;
	}

	/**
	 * Getter sandbox
	 * 
	 * @return
	 */
	private SandboxInf getSandbox() {
		return sandbox;
	}

	@Override
	public void run() {
		super.run();

		if (getSandbox().isInitialized()) {

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
			getSandbox().getServerActionHandler().perform("/register",
					new RegisterMeasureInstrumnet.ParamBuilder().setConfiguration(ApplicationConfigurationHelper.getInstrumentConfiguration(getSandbox().getConfiguration())).build());
			getLogger().debug(" ping server done ...");

		} catch (Exception e) {
			getLogger().error("ping server failed", e);
		}

		try {
			getLogger().debug(" try load data from db");
			// appl.getJpaActionHandler().perform("GET-mlog", new
			// HashMap<String, Object>());
			getSandbox().getJPAActionHandler().perform("GET-mlog", new HashMap<String, Object>());
		} catch (Exception e) {
			getLogger().error("load data failed", e);
		}

	}
}
