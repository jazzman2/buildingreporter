/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Arduino Action Handler Abstract
 * 
 * @author jkovalci
 * 
 */
public abstract class ArduinoActionHandlerAbt implements ArduinoActionHandlerInf {
	private final Map<String, ArduinoActionInf> actionRegister = new HashMap<String, ArduinoActionInf>();
	private Map<String, Object> configuration;

	/**
	 * {@link Constructor}
	 * 
	 * @param configuraion
	 * 
	 */
	public ArduinoActionHandlerAbt() {
		// init(configuraion);
	}

	/**
	 * Register Actions
	 */
	public abstract void registerActions();

	/**
	 * Init handler
	 * 
	 * @param configuraion
	 */
	@Override
	public void init(Map<String, Object> configuraion) {
		registerActions();
		setConfiguration(configuraion);
	}

	/**
	 * Reinit Handler
	 * 
	 * @param configuration
	 */
	protected void reinit(Map<String, Object> configuration) {

	}

	@Override
	public void registerAction(String actionName, ArduinoActionInf action) {
		if (actionName == null || action == null) {
			throw new IllegalArgumentException("Null argument");
		}

		actionRegister.put(actionName, action);
	}

	@Override
	public void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}

	@Override
	public Map<String, Object> getConfiguration() {
		return configuration;
	}

	@Override
	public Map<String, Object> callAction(String actionName, Map<String, Object> actionParam) throws Exception {
		if (actionName == null) {
			throw new IllegalArgumentException("Null argument");
		}

		ArduinoActionInf action = actionRegister.get(actionName);

		Map<String, Object> retVal;

		if (action != null) {
			Map<String, Object> arduinoActionParam = buildArduinoActionParam(actionParam);
			retVal = action.performAction(arduinoActionParam);
		} else {
			throw new IllegalStateException("Null object!");
		}

		return retVal;
	}

	/**
	 * Build Arduino Action Param
	 * 
	 * @param actionParam
	 * @return
	 */
	public Map<String, Object> buildArduinoActionParam(Map<String, Object> actionParam) {

		// TODO: pridaj configuration
		return actionParam;
	}
}
