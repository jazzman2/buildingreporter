package sk.jazzman.buildingreporter.application;

import java.util.Map;

/**
 * Arduino Action
 * 
 * @author jkovalci
 * 
 */
public interface ArduinoActionInf {

	/**
	 * Perform Action On Arduino Board
	 * 
	 * @param actionParam
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> performAction(Map<String, Object> actionParam) throws Exception;
}
