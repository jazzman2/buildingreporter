/**
 * 
 */
package sk.jazzman.brmi.arduino;

import java.util.Map;

import org.apache.commons.configuration.Configuration;

/**
 * @author jkovalci
 * 
 */
public interface ArduinoManagerInf {

	/**
	 * Register Arduino Handlers
	 * 
	 * @param name
	 * @param handler
	 * @param configuration
	 * 
	 */
	public void registerArduinoHandler(String name, ArduinoActionHandlerInf handler, Configuration configuration);

	/**
	 * Call Action On Arduino
	 * 
	 * @param actionName
	 * @param actionParameters
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map<String, Object>> callAction(String actionName, Map<String, Object> actionParameters) throws Exception;

}
