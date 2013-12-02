/**
 * 
 */
package sk.jazzman.brmi.common;

import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Parameter Builder
 * 
 * @author jano
 * 
 */
public class ParameterBuilder {
	private final Map<String, Object> parameters = new HashMap<String, Object>();

	/**
	 * {@link Constructor}
	 * 
	 */
	public ParameterBuilder() {

	}

	/**
	 * {@link Constructor}
	 * 
	 * @param parameters
	 */
	public ParameterBuilder(Map<String, Object> parameters) {
		if (parameters == null) {
			throw new IllegalArgumentException("Null argument!");
		}
		parameters.putAll(parameters);
	}

	/**
	 * Set Configuration
	 * 
	 * @param configuration
	 * @return
	 */
	public ParameterBuilder setConfiguration(Configuration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		StringWriter sw = new StringWriter();

		try {
			((XMLConfiguration) configuration).save(sw);
		} catch (ConfigurationException e) {
			throw new IllegalStateException("Coult not to save configuration!");
		}

		parameters.put(ParameterGetter.INPUT_CONFIGURATION, sw.toString());

		return this;
	}

	/**
	 * Setter parameter
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ParameterBuilder setParameter(String key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		parameters.put(key, value);

		return this;
	}

	/**
	 * Build params
	 * 
	 * @return
	 */
	public Map<String, Object> build() {
		return parameters;
	}
}
