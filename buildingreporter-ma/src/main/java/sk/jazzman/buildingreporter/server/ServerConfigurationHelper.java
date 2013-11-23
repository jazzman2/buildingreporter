/**
 * 
 */
package sk.jazzman.buildingreporter.server;

import java.util.Map;

/**
 * @author jano
 * 
 */
public final class ServerConfigurationHelper {
	public static final String SERVER_URL = "buildingreporter.mi.server.url";
	public static final String MEASURE_INSTRUMENT_NAME = "buildingreporter.mi.measureinstrumnet.name";

	/**
	 * Get Server url from configuration
	 * 
	 * @param configuration
	 * @return
	 */
	public static String getServerURL(Map<String, Object> configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		String retVal = (String) configuration.get(SERVER_URL);

		if (retVal == null) {
			throw new IllegalStateException("Null value!");
		}

		return retVal;
	}

	/**
	 * Return Measure Instrument name
	 * 
	 * @param configuration
	 * @return
	 */
	public static String getMeasureInstrumentName(Map<String, Object> configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		String retVal = (String) configuration.get(MEASURE_INSTRUMENT_NAME);

		if (retVal == null) {
			throw new IllegalStateException("Null value!");
		}

		return retVal;
	}
}
