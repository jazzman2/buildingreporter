/**
 *
 */
package sk.jazzman.brmi.arduino;

import org.apache.commons.configuration2.Configuration;

/**
 * @author jano
 */
public final class ArduinoConfigurationHepler {
	
	/**
	 * private constructor
	 */
	private ArduinoConfigurationHepler() {
		
	}
	
	/**
	 * Return port name
	 *
	 * @param configuration
	 *
	 * @return
	 */
	public static final String getPort(Configuration configuration) {
		String retVal = configuration.getString("arduino/serialport/name", null);
		
		if(retVal == null) {
			throw new IllegalArgumentException("Null value!");
		}
		
		return retVal;
	}
	
	/**
	 * Return port time out
	 *
	 * @param configuration
	 *
	 * @return
	 */
	public static final Integer getPortTimeOut(Configuration configuration) {
		Integer retVal = configuration.getInteger("arduino/serialport/timeout", null);
		
		if(retVal == null) {
			throw new IllegalArgumentException("Null value!");
		}
		
		return retVal;
	}
	
	/**
	 * Return port bitrate
	 *
	 * @param configuration
	 *
	 * @return
	 */
	public static Integer getBitrate(Configuration configuration) {
		Integer retVal = configuration.getInteger("arduino/serialport/bitrate", null);
		
		if(retVal == null) {
			throw new IllegalArgumentException("Null value!");
		}
		
		return retVal;
	}
}
