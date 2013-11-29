/**
 * 
 */
package sk.jazzman.brmi.common;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Default Action Handler
 * 
 * @author jano
 * 
 */
public abstract class DefaultActionHandlerAbt<A extends ActionInf> implements ActionHandlerInf<A> {

	protected ActionRegisterInf<A> actionRegister;
	private Map<String, Object> configuration;

	/**
	 * {@link Constructor}
	 * 
	 */
	public DefaultActionHandlerAbt() {

	}

	@Override
	public ActionRegisterInf<A> getActionRegister() {
		return actionRegister;
	}

	@Override
	public Map<String, Object> getConfiguration() {
		return configuration;
	}

	/**
	 * init action handler
	 * 
	 * @param configuration
	 * 
	 */
	@Override
	public void init(Map<String, Object> configuration) throws Exception {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		this.configuration = configuration;
	}

}
