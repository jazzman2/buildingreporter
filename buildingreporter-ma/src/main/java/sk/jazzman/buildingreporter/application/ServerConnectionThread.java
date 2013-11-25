/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.server.ServerActionHandlerInf;
import sk.jazzman.buildingreporter.server.ws.RegisterMeasureInstrumnet;

/**
 * @author jkovalci
 * 
 */
public class ServerConnectionThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(ServerConnectionThread.class);

	private ServerActionHandlerInf serverActionHandler;
	private Map<String, Object> configuration;

	/**
	 * {@link Constructor}
	 * 
	 */
	public ServerConnectionThread() {

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
	 * Setter Server Action Handler
	 * 
	 * @param serverActionHandler
	 */
	public void setServerActionHandler(ServerActionHandlerInf serverActionHandler) {
		this.serverActionHandler = serverActionHandler;
	}

	/**
	 * Setter configuration
	 * 
	 * @param configuration
	 */
	public void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}

	private Map<String, Object> getConfiguration() {
		return configuration;
	}

	/**
	 * Getter Server Action Handler
	 * 
	 * @return
	 */
	private ServerActionHandlerInf getServerActionHandler() {
		return serverActionHandler;
	}

	@Override
	public void run() {
		super.run();

		if (getServerActionHandler() != null && getConfiguration() != null) {

			for (int index = 1; index < 100; index++) {
				ping();

				try {
					sleep(10000l);
				} catch (InterruptedException e) {
					getLogger().debug(" sleep error", e);
					return;
				}
			}
		} else {
			getLogger().error("Not initialyzed yet.");
		}

	}

	/**
	 * Ping server
	 */
	protected void ping() {
		try {

			getLogger().debug(" ping server ...");
			getServerActionHandler().call("/register", new RegisterMeasureInstrumnet.ParamBuilder().setConfiguration(getConfiguration()).build());
			getLogger().debug(" ping server done ...");
		} catch (Exception e) {
			getLogger().error("ping server failed", e);
		}
	}
}
