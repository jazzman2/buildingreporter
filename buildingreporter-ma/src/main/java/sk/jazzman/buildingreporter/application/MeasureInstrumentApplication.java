/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.arduino.ArduinoManagerInf;
import sk.jazzman.buildingreporter.arduino.DefaultArduinoActionManager;
import sk.jazzman.buildingreporter.server.ServerActionHandlerInf;
import sk.jazzman.buildingreporter.server.ServerConfigurationHelper;
import sk.jazzman.buildingreporter.server.ws.WSServerActionHandler;

/**
 * @author jkovalci
 * 
 */
public class MeasureInstrumentApplication implements MeasureInstrumentInf {

	private ArduinoManagerInf arduinoManager;
	private ServerActionHandlerInf serverActionHandler;
	private Map<String, Object> configuration;

	private static final Logger logger = LoggerFactory.getLogger(MeasureInstrumentApplication.class);

	/**
	 * {@link Constructor}
	 */
	public MeasureInstrumentApplication() {

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
	 * Init Application
	 */
	public void init() throws Exception {
		registerConfigurationHandler();
		registerServerHandlers();
		registerArduinoHandlers();
	}

	@Override
	public void registerServerHandlers() throws Exception {
		Map<String, Object> config = new HashMap<String, Object>();
		config.putAll(getConfiguration());

		serverActionHandler = new WSServerActionHandler();
		serverActionHandler.init(config);
	}

	@Override
	public void registerConfigurationHandler() {
		configuration = new HashMap<String, Object>();
		configuration.put(ServerConfigurationHelper.SERVER_URL, "http://localhost:8080/buildingreporter-web/mi");
	}

	public void registerArduinoHandlers() {
		arduinoManager = new DefaultArduinoActionManager();
		try {
			((DefaultArduinoActionManager) arduinoManager).init();
		} catch (Exception e) {
			getLogger().error("Could not to init arduino action manager", e);
		}
	}

	/**
	 * Return configuration
	 * 
	 * @return
	 */
	public Map<String, Object> getConfiguration() {
		return configuration;
	}

	/**
	 * Getter Server Action Handler
	 * 
	 * @return
	 */
	public ServerActionHandlerInf getServerActionHandler() {
		return serverActionHandler;
	}
}
