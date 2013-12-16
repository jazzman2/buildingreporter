/**
 * 
 */
package sk.jazzman.buildingreporter.domain.instrument;

import org.apache.commons.configuration.Configuration;

/**
 * Instrument utilities class
 * 
 * @author jano
 * 
 */
public final class InstrumentUtils {
	private InstrumentUtils() {

	}

	/**
	 * 
	 * Return name of measure instrument
	 * 
	 * @param configuration
	 * @return
	 * 
	 */
	public static String getName(Configuration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		return configuration.getString("mi/name", null);
	}
}
