/**
 * 
 */
package sk.jazzman.brmi.server;

import java.util.Map;

import org.apache.commons.configuration.Configuration;

import sk.jazzman.brmi.application.ApplicationConfigurationHelper;

/**
 * @author jano
 * 
 */
public final class ServerConfigurationHelper {
	public static final String SERVER = "buildingreporter.mi.server";
	public static final String SERVER_URL = "buildingreporter.mi.server.url";

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
	 * Return server subconfiguration
	 * 
	 * @param configuration
	 * @return
	 */
	public static Map<String, Object> getServerConfiguration(Configuration configuration) {
		return ApplicationConfigurationHelper.getSubconfiguration(configuration, SERVER);
	}
}
