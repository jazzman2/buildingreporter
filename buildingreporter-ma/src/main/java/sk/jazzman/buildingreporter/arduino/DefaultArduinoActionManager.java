/**
 * 
 */
package sk.jazzman.buildingreporter.arduino;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default Arduino Manager
 * 
 * @author jkovalci
 * 
 */
public class DefaultArduinoActionManager implements ArduinoManagerInf {
	private final Logger log = LoggerFactory.getLogger(DefaultArduinoActionManager.class);

	private final Map<String, ArduinoActionHandlerInf> handlers = new HashMap<String, ArduinoActionHandlerInf>();

	/**
	 * {@link Constructor}
	 * 
	 */
	public DefaultArduinoActionManager() {

	}

	/**
	 * Init Manager
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {
		registerHandlers();
	}

	/**
	 * Register handlers
	 */
	protected void registerHandlers() {
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ArduinoConfigurationHepler.PARAM_ARDUINO_SERIAL_PORT_NAME, "/dev/ttyUSB0");
		config.put(ArduinoConfigurationHepler.PARAM_ARDUINO_BITRATE, Integer.valueOf(9600));
		config.put(ArduinoConfigurationHepler.PARAM_ARDUINO_TIME_OUT, Integer.valueOf(2000));

		ArduinoActionHandlerInf ah = new DefaultArduinoActionHandler();

		registerArduinoHandler("a1", ah, config);
	}

	protected Map<String, ArduinoActionHandlerInf> getHandlers() {
		return handlers;
	}

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	protected Logger getLogger() {
		return log;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.jazzman.buildingreporter.application.ArduinoManagerInf#
	 * registerArduinoHandler(java.lang.String,
	 * sk.jazzman.buildingreporter.application.ArduinoActionHandlerInf,
	 * java.util.Map)
	 */
	@Override
	public void registerArduinoHandler(String name, ArduinoActionHandlerInf handler, Map<String, Object> configuration) {

		getLogger().debug("Start registering handler: " + name);

		try {
			handler.init(configuration);
		} catch (Exception e) {
			getLogger().error("Could not to register handler:  " + name);
			return;
		}

		handlers.put(name, handler);

		getLogger().debug("Handler has been registered: " + name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sk.jazzman.buildingreporter.application.ArduinoManagerInf#callAction(
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Map<String, Map<String, Object>> callAction(String actionName, Map<String, Object> actionParameters) throws Exception {

		if (!handlers.isEmpty()) {
			Map<String, Map<String, Object>> retVal = new HashMap<String, Map<String, Object>>();
			Map<String, Object> r;
			for (Map.Entry<String, ArduinoActionHandlerInf> e : handlers.entrySet()) {
				r = callAction(actionName, e.getValue(), actionParameters);

				retVal.put(e.getKey(), r);
			}
		}

		return null;
	}

	/**
	 * Call Action On Handler
	 * 
	 * @param actionName
	 * @param handlerName
	 * @param actionParameters
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> callAction(String actionName, ArduinoActionHandlerInf handler, Map<String, Object> actionParam) throws Exception {
		if (actionName == null || handler == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		Map<String, Object> retVal;

		try {
			retVal = handler.callAction(actionName, actionParam);
		} catch (Exception e) {
			retVal = null;
			getLogger().error("Could not to call " + actionName + " on " + handler);
		}

		return retVal;
	}
}
