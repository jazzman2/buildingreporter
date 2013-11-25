/**
 * 
 */
package sk.jazzman.buildingreporter.server.ws;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import sk.jazzman.buildingreporter.server.ServerActionInf;
import sk.jazzman.buildingreporter.server.ServerActionRegisterInf;

/**
 * Default Server Action Register
 * 
 * @author jano
 * 
 */
public class DefaultActionRegister implements ServerActionRegisterInf {

	private final Map<String, ServerActionInf> register = new HashMap<String, ServerActionInf>();

	/**
	 * {@link Constructor}
	 * 
	 */
	public DefaultActionRegister() {

	}

	/**
	 * Register Actions
	 */
	@Override
	public void registerActions() throws Exception {
		registerAction(RegisterMeasureInstrumnet.getName(), new RegisterMeasureInstrumnet());
	}

	@Override
	public void registerAction(String name, ServerActionInf action) {
		if (name == null || action == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		if (register.containsKey(action)) {
			throw new IllegalStateException("Action " + name + " already registered!");
		}

		register.put(name, action);
	}

	@Override
	public ServerActionInf getAction(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		ServerActionInf action = register.get(name);

		if (action == null) {
			throw new IllegalStateException("Action " + name + " not registered");
		}

		return action;
	}

}
