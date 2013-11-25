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

}
