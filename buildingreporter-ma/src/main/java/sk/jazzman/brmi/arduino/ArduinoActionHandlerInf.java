/**
 * 
 */
package sk.jazzman.brmi.arduino;

import java.util.Map;

import sk.jazzman.brmi.common.ActionHandlerInf;

/**
 * Arduino Action Handler
 * 
 * @author jkovalci
 * 
 */
public interface ArduinoActionHandlerInf extends ActionHandlerInf<ArduinoActionInf> {

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
