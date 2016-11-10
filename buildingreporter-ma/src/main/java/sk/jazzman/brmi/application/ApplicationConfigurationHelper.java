/**
 *
 */
package sk.jazzman.brmi.application;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.HierarchicalConfiguration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jkovalci
 */
public class ApplicationConfigurationHelper {
	
	/**
	 * @param configuration
	 * @param prefix
	 *
	 * @return
	 */
	public static Map<String, Object> getSubconfiguration(Configuration configuration, String prefix) {
		if(configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}
		
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		Iterator<String> keys = configuration.getKeys();
		
		while(keys.hasNext()) {
			String k = keys.next();
			if(prefix == null || k.startsWith(prefix)) {
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
		if(configuration == null) {
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
		if(configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}
		
		((HierarchicalConfiguration) configuration).addProperty("mi/ip_address", ipAddress);
	}
	
	/**
	 * Getter Measure instrument name
	 *
	 * @param configuration
	 *
	 * @return
	 */
	public static final String getName(Configuration configuration) {
		if(configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}
		
		return ((HierarchicalConfiguration) configuration).getString("mi/name", null);
	}
}
