/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.util.Map;

/**
 * Arduino Action Handler
 * 
 * @author jkovalci
 * 
 */
public interface ArduinoActionHandlerInf {

	/**
	 * Init handler
	 * 
	 * @param configuration
	 */
	public void init(Map<String, Object> configuration) throws Exception;

	/**
	 * Configure Arduino
	 * 
	 * @param configuration
	 */
	public void setConfiguration(Map<String, Object> configuration);

	/**
	 * Getter arduino configuration
	 * 
	 * @return
	 */
	public Map<String, Object> getConfiguration();

	/**
	 * Register Action
	 * 
	 * @param actionName
	 * @param action
	 */
	public void registerAction(String actionName, ArduinoActionInf action);

	/**
	 * Call action
	 * 
	 * @param actionName
	 * @param actionParam
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> callAction(String actionName, Map<String, Object> actionParam) throws Exception;
}
