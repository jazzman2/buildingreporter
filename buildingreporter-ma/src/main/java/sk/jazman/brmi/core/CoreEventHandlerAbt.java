/**
 * 
 */
package sk.jazman.brmi.core;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Core Event Handler
 * 
 * @author jkovalci
 * 
 */
public abstract class CoreEventHandlerAbt implements CoreEventHandlerInf {

	private final Map<String, CoreEventResolverInf> register = new HashMap<String, CoreEventResolverInf>();

	/**
	 * {@link Constructor}
	 */
	public CoreEventHandlerAbt() {
		registerResolvers();
	}

	@Override
	public void handle(CoreEventInf event) throws Exception {
		if (event == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		String name = event.getName();
		for (Map.Entry<String, CoreEventResolverInf> e : register.entrySet()) {
			if (name.equals(e.getKey())) {
				e.getValue().resolve(event);
			}
		}
	}

	@Override
	public void register(String name, CoreEventResolverInf resolver) {
		if (name == null || resolver == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		if (register.containsKey(name)) {
			throw new IllegalStateException("Resolver " + name + " already registered!");
		}

		register.put(name, resolver);
	}

	@Override
	public CoreEventResolverInf unregister(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		return register.remove(name);
	}

}
