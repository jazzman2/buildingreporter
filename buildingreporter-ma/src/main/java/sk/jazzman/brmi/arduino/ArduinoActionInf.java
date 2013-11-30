package sk.jazzman.brmi.arduino;

import java.util.Map;

import sk.jazzman.brmi.common.ActionInf;

/**
 * Arduino Action
 * 
 * @author jkovalci
 * 
 */
public interface ArduinoActionInf extends ActionInf {

	/**
	 * Perform Action On Arduino Board
	 * 
	 * @param actionParam
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> performAction(Map<String, Object> actionParam) throws Exception;
}
