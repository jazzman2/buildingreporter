/**
 * 
 */
package sk.jazzman.brmi.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jano
 * 
 */
public abstract class DefaultActionRegisterAbt<A extends ActionInf> implements ActionRegisterInf<A> {
	private final Map<String, A> register = new HashMap<String, A>();

	@Override
	public void registerAction(String name, A action) {
		if (name == null || action == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		if (register.containsKey(name)) {
			throw new IllegalStateException("Action '" + name + "' alredy registered!");
		}

		register.put(name, action);
	}

	@Override
	public A getAction(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		A retVal = register.get(name);

		if (retVal == null) {
			throw new IllegalStateException("Action '" + name + "' not registered");
		}

		return retVal;
	}
}
