/**
 * 
 */
package sk.jazzman.buildingreporter.domain.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jano
 * 
 */
public class ActionParamBuilder {
	private final Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * Put parameter
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ActionParamBuilder put(String key, Object value) {
		params.put(key, value);
		return this;
	}

	/**
	 * Build parameters
	 * 
	 * @return
	 */
	public Map<String, Object> build() {
		return params;
	}
}
