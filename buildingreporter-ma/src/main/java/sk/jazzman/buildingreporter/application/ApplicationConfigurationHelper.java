/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.util.Map;

/**
 * @author jkovalci
 * 
 */
public class ApplicationConfigurationHelper {
	public static final String MEASURE_INSTRUMENT_NAME = "buildingreporter.mi.measureinstrumnet.name";
	public static final String MEASURE_INSTRUMENT_MAC_ADDRESS = "buildingreporter.mi.measureinstrumnet.mac_address";
	public static final String MEASURE_INSTRUMENT_IP_ADDRESS = "buildingreporter.mi.measureinstrumnet.ip_address";

	/**
	 * Return value from map safety
	 * 
	 * @param attribure
	 * @param configuration
	 * @return
	 */
	private static Object getSafetyValue(String attribure, Map<String, Object> configuration) {
		if (configuration == null || attribure == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		Object retVal = configuration.get(attribure);

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
	public static Object getMeasureInstrumentName(Map<String, Object> configuration) {
		return getSafetyValue(MEASURE_INSTRUMENT_NAME, configuration);
	}

	/**
	 * Return Measure Instrument MAC address
	 * 
	 * @param configuration
	 * @return
	 */
	public static Object getMeasureInstrumentMacAddress(Map<String, Object> configuration) {
		return getSafetyValue(MEASURE_INSTRUMENT_MAC_ADDRESS, configuration);
	}

	/**
	 * Return Measure Instrument IP address
	 * 
	 * @param configuration
	 * @return
	 */
	public static Object getMeasureInstrumentIpAddress(Map<String, Object> configuration) {
		return getSafetyValue(MEASURE_INSTRUMENT_IP_ADDRESS, configuration);
	}
}
