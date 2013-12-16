/**
 * 
 */
package sk.jazzman.buildingreporter.domain.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Default register
 * 
 * @author jano
 * 
 */
public abstract class DefaultRegisterAbt<T extends Object> implements RegisterInf<T> {
	private final Map<String, T> register = new HashMap<String, T>();

	@Override
	public synchronized void register(String name, T action) {
		if (name == null || action == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		if (register.containsKey(name)) {
			throw new IllegalStateException("Action '" + name + "' alredy registered!");
		}

		register.put(name, action);
	}

	@Override
	public synchronized T get(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		T retVal = register.get(name);

		if (retVal == null) {
			throw new IllegalStateException("Action '" + name + "' not registered");
		}

		return retVal;
	}

	/**
	 * Unregister object
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public synchronized T unregister(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		return register.remove(name);
	}

	/**
	 * Unregister all objects
	 */
	@Override
	public synchronized void unregisterAll() {
		register.clear();
	}
}
