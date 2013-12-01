/**
 * 
 */
package sk.jazman.brmi.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jano
 * 
 */
public class CoreEventManager implements CoreEventManagerInf {

	private final Map<String, CoreEventHandlerInf> register = new HashMap<String, CoreEventHandlerInf>();

	@Override
	public void fireEvent(CoreEventInf coreEvent) {

	}

	@Override
	public void registerHandler(String name, CoreEventHandlerInf handler) {
		if (register.containsKey(name)) {
			throw new IllegalArgumentException("Null value!");
		}
	}

}
