/**
 * 
 */
package sk.jazzman.brmi.application;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.common.ParameterBuilder;
import sk.jazzman.brmi.server.ws.ServerActionHandlerStateInf;
import sk.jazzman.brmi.server.ws.WSServerActionHandler;
import sk.jazzman.brmi.server.ws.action.Ping;

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
	 * @param a
	 * @param sandbox
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
	private synchronized SandboxInf getSandbox() {
		return sandbox;
	}

	@Override
	public void run() {
		super.run();

		while (true) {
			if (getSandbox().isInitialized()) {
				check();
			}
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
		WSServerActionHandler sh = (WSServerActionHandler) getSandbox().getServerActionHandler();

		try {
			if (!sh.isInitialized()) {
				getLogger().debug("init server action handler...");

				sh.init(getSandbox());

				getLogger().debug("init server action handler ... done ");

			}

		} catch (Exception e) {
			getLogger().error("Init server action handler failed", e);
			sh.getState().setInitializationState(ServerActionHandlerStateInf.NOT_INITIALIZED);
		}

		try {
			if (!sh.isConnected()) {
				sh.perform(Ping.getName(), new ParameterBuilder().build());
			}

			// getLogger().debug(" try load data from db");
			// // appl.getJpaActionHandler().perform("GET-mlog", new
			// // HashMap<String, Object>());
			// getSandbox().getJPAActionHandler().perform("GET-mlog", new
			// HashMap<String, Object>());
		} catch (Exception e) {
			getLogger().error("Connect  data failed", e);
			sh.getState().setConnectionState(ServerActionHandlerStateInf.DISCONNECTED);
		}

	}
}
