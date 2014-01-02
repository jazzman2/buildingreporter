/**
 * 
 */
package sk.jazzman.brmi.application;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.common.ParameterBuilder;
import sk.jazzman.brmi.server.ws.action.Ping;

/**
 * @author jkovalci
 * 
 */
public class ServerConnectionThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(ServerConnectionThread.class);

	private final Sandbox sandbox;
	private final Application application;

	/**
	 * {@link Constructor}
	 * 
	 * @param a
	 * @param sandbox
	 * 
	 */
	public ServerConnectionThread(Application a, Sandbox sandbox) {
		this.sandbox = sandbox;
		this.application = a;
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
	private synchronized SandboxInf getSandbox() {
		return sandbox;
	}

	private synchronized Application getApplication() {
		return application;
	}

	@Override
	public void run() {
		super.run();

		if (getSandbox().isInitialized()) {

			for (int index = 1; index < 100; index++) {
				check();

				try {
					sleep(100000l);
				} catch (InterruptedException e) {
					getLogger().debug(" sleep error", e);
					return;
				}
			}
		} else {
			getLogger().error("Not initialyzed yet.");

			try {
				sleep(100000l);
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
			if (getApplication().hasServerConnection()) {
				getLogger().debug("ping server ...");
				getSandbox().getServerActionHandler().perform(Ping.getName(),
						new ParameterBuilder().setParameter("configuration", getSandbox().getXStreamManager().toXML(getSandbox().getConfiguration())).build());
				getLogger().debug("Ping server ... done ");

			} else {
				// if (getSandbox().isInitialized()) {
				// getLogger().info("Register measure instrument");
				// getSandbox().getServerActionHandler().perform(RegisterMeasureInstrumnet.getName(),
				// new ParameterBuilder().setParameter("configuration",
				// getSandbox().getConfiguration()).build());
				// getLogger().info("Register measure instrument ... done");
				// }
			}

		} catch (Exception e) {
			getLogger().error("Server action call failed", e);
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
