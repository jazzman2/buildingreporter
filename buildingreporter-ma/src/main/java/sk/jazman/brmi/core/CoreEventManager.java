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
	public void fireEvent(CoreEventInf coreEvent) throws Exception {
		if (coreEvent == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		for (CoreEventHandlerInf h : register.values()) {
			h.handle(coreEvent);
		}
	}

	@Override
	public void register(String name, CoreEventHandlerInf handler) {
		if (name == null || handler == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		if (register.containsKey(name)) {
			throw new IllegalStateException("Core Event Handler " + name + " already registered!");
		}

		register.put(name, handler);
	}

	@Override
	public void unregister(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		register.remove(name);
	}

	@Override
	public CoreEventHandlerInf get(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		return register.get(name);
	}
}
