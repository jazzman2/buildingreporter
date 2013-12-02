/**
 * 
 */
package sk.jazzman.brmi.common;

import java.util.Map;

/**
 * @author jkovalci
 * 
 */
public class ParameterGetter {

	public static final String INPUT_CONFIGURATION = "p_input_configuration";

	// private final Map<String, Object> actionParams;
	//
	// /**
	// * {@link Constructor}
	// *
	// * @param actionParams
	// */
	// public ParameterGetter(Map<String, Object> parameters) {
	// this.parameters = parameters;
	// }

	public String getConfiguration(Map<String, Object> parameters) {
		String retVal = (String) parameters.get(INPUT_CONFIGURATION);

		return retVal;
	}

	/**
	 * Return action param
	 * 
	 * @param key
	 * @param clazz
	 * @param actionParams
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz, Map<String, Object> actionParams) {
		if (key == null || clazz == null || actionParams == null) {
			throw new IllegalArgumentException("Null argument");
		}

		Object retVal = actionParams.get(key);

		if (retVal != null && !clazz.isAssignableFrom(retVal.getClass())) {
			throw new IllegalStateException("Not correct class type!");
		}

		return (T) retVal;
	}

}
