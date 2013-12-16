/**
 * 
 */
package sk.jazzman.buildingreporter.common;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterMatcher;

/**
 * @author jano
 * 
 */
public abstract class EntityConverterAbt implements Converter {
	private final Map<String, AttributeHandler> attributeConverters = new HashMap<String, AttributeHandler>();

	/**
	 * {@link Constructor}
	 */
	public EntityConverterAbt() {
		registerAttributeHandlers();
	}

	/**
	 * Register Attribute Handlers
	 */
	protected abstract void registerAttributeHandlers();

	/**
	 * Register Attribute Handler
	 * 
	 * @see AttributeHandler
	 * 
	 * @param key
	 * @param handler
	 */
	protected void register(String key, AttributeHandler handler) {
		if (key == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		if (attributeConverters.containsKey(key)) {
			throw new IllegalStateException("Handler alredy registered!");
		}

		attributeConverters.put(key, handler);
	}

	/**
	 * Return register
	 * 
	 * @return
	 */
	protected Map<String, AttributeHandler> getRegister() {
		return attributeConverters;
	}

	/**
	 * Attribute handler
	 * 
	 * @author jano
	 * 
	 */
	protected static class AttributeHandler {
		private final String beanAttributeName;
		private final ConverterMatcher converter;

		/**
		 * {@link Constructor}
		 * 
		 * @param beanAttributeName
		 * @param converter
		 */
		public AttributeHandler(String beanAttributeName, ConverterMatcher converter) {
			this.beanAttributeName = beanAttributeName;
			this.converter = converter;
		}

		/**
		 * Getter Bean Attribute Name
		 * 
		 * @return
		 */
		public String getBeanAttributeName() {
			return beanAttributeName;
		}

		/**
		 * Getter {@link ConverterMatcher}
		 * 
		 * @return
		 */
		public ConverterMatcher getConverter() {
			return converter;
		}
	}
}
