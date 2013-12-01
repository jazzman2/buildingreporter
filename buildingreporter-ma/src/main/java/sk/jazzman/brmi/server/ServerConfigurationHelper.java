/**
 * 
 */
package sk.jazzman.brmi.server;

import org.apache.commons.configuration.Configuration;

/**
 * @author jano
 * 
 */
public final class ServerConfigurationHelper {

	/**
	 * Get Server url from configuration
	 * 
	 * @param configuration
	 * @return
	 */
	public static final String getServerURL(Configuration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		String retVal = configuration.getString("server/url", null);

		if (retVal == null) {
			throw new IllegalStateException("Null value!");
		}

		return retVal;
	}
}
