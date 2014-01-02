/**
 * 
 */
package sk.jazzman.buildingreporter.domain.building;

import org.apache.commons.configuration.Configuration;

/**
 * @author jano
 * 
 */
public final class BuildingConfigurationHelper {
	private BuildingConfigurationHelper() {

	}

	/**
	 * Return undefined building part
	 * 
	 * @param configuration
	 * @return
	 * 
	 * @author jano
	 */
	public static BPart getUndefinedBuilding(final Configuration configuration) {
		// FIXME: id from configuration
		return BPart.findBPart(Long.valueOf(-1));
	}
}
