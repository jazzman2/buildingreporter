/**
 * 
 */
package sk.jazzman.brmi.application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalConfiguration;

/**
 * @author jkovalci
 * 
 */
public class ApplicationConfigurationHelper {
	// public static final String MI = "buildingreporter.mi";
	// public static final String MI_NAME =
	// "buildingreporter.mi.measureinstrumnet.name";
	// public static final String MI_MAC_ADDRESS =
	// "buildingreporter.mi.measureinstrumnet.mac_address";
	// public static final String MI_IP_ADDRESS =
	// "buildingreporter.mi.measureinstrumnet.ip_address";
	//
	// /**
	// * Return value from map safety
	// *
	// * @param attribure
	// * @param configuration
	// * @return
	// */
	// public static Object getSafetyValue(String attribure, Map<String, Object>
	// configuration) {
	// if (configuration == null || attribure == null) {
	// throw new IllegalArgumentException("Null argument!");
	// }
	//
	// Object retVal = configuration.get(attribure);
	//
	// if (retVal == null) {
	// throw new IllegalStateException("Null value!");
	// }
	//
	// return retVal;
	// }
	//
	// /**
	// * Return value from map safety
	// *
	// * @param attribure
	// * @param configuration
	// * @return
	// */
	// public static Object getSafetyValue(String attribure, Configuration
	// configuration) {
	// if (configuration == null || attribure == null) {
	// throw new IllegalArgumentException("Null argument!");
	// }
	//
	// Object retVal = configuration.getProperty(attribure);
	//
	// if (retVal == null) {
	// throw new IllegalStateException("Null value!");
	// }
	//
	// return retVal;
	// }
	//
	// /**
	// * Return Measure Instrument name
	// *
	// * @param configuration
	// * @return
	// */
	// public static Object getMeasureInstrumentName(Map<String, Object>
	// configuration) {
	//
	// return getSafetyValue(MI_NAME, configuration);
	// }
	//
	// /**
	// * Return Measure Instrument MAC address
	// *
	// * @param configuration
	// * @return
	// */
	// public static Object getMeasureInstrumentMacAddress(Map<String, Object>
	// configuration) {
	// return getSafetyValue(MI_MAC_ADDRESS, configuration);
	// }
	//
	// /**
	// * Return Measure Instrument IP address
	// *
	// * @param configuration
	// * @return
	// */
	// public static Object getMeasureInstrumentIpAddress(Map<String, Object>
	// configuration) {
	// return getSafetyValue(MI_IP_ADDRESS, configuration);
	// }
	//
	// /**
	// * Return measure instrument subconfiguration
	// *
	// * @param configuration
	// * @return
	// */
	// public static Map<String, Object>
	// getInstrumentConfiguration(Configuration configuration) {
	// return getSubconfiguration(configuration, MI);
	// }

	/**
	 * 
	 * @param configuration
	 * @param prefix
	 * @return
	 */
	public static Map<String, Object> getSubconfiguration(Configuration configuration, String prefix) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		Map<String, Object> retVal = new HashMap<String, Object>();

		Iterator<String> keys = configuration.getKeys();

		while (keys.hasNext()) {
			String k = keys.next();
			if (prefix == null || k.startsWith(prefix)) {
				retVal.put(k, configuration.getProperty(k));
			}
		}

		return retVal;
	}

	/**
	 * Set mac address to configuration
	 * 
	 * @param macAddress
	 * @param configuration
	 */
	public static final void setMacAddress(String macAddress, Configuration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		((HierarchicalConfiguration) configuration).addProperty("mi/mac_address", macAddress);
	}

	/**
	 * Set ip address to configuration
	 * 
	 * @param macAddress
	 * @param configuration
	 */
	public static final void setIpAddress(String ipAddress, Configuration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		((HierarchicalConfiguration) configuration).addProperty("mi/ip_address", ipAddress);
	}
}
