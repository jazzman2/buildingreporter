/**
 * 
 */
package sk.jazzman.brmi.common;

import java.util.Map;

/**
 * @author jano
 * 
 */
public final class ActionParamGetter {

	/**
	 * Return action param
	 * 
	 * @param key
	 * @param clazz
	 * @param actionParams
	 * @return
	 */
	public static final <T> T get(String key, Class<T> clazz, Map<String, Object> actionParams) {
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
