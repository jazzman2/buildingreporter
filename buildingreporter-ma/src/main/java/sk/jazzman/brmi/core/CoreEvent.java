/**
 * 
 */
package sk.jazzman.brmi.core;

import java.lang.reflect.Constructor;

/**
 * Core Event Abstract
 * 
 * @author jano
 * 
 */
public class CoreEvent implements CoreEventInf {

	private final String name;
	private final Object parameters;
	private final Object source;

	/**
	 * {@link Constructor}
	 * 
	 * @param name
	 * @param parameters
	 * @param source
	 */
	public CoreEvent(String name, Object parameters, Object source) {
		this.name = name;
		this.parameters = parameters;
		this.source = source;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getParameters() {
		return parameters;
	}

	@Override
	public Object getSource() {
		return source;
	}

}
