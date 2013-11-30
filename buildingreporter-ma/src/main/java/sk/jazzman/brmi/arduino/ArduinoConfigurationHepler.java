/**
 * 
 */
package sk.jazzman.brmi.arduino;

import org.apache.commons.configuration.Configuration;

/**
 * @author jano
 * 
 */
public final class ArduinoConfigurationHepler {

	public static final String ARDUINO = "buildingreporter.mi.arduino";

	public static final String SERIAL_PORT_NAME = "buildingreporter.mi.arduino.serialport.name";
	public static final String TIME_OUT = "buildingreporter.mi.arduino.serialport.timeout";
	public static final String BITRATE = "buildingreporter.mi.arduino.serialport.bitrate";

	/**
	 * private constructor
	 */
	private ArduinoConfigurationHepler() {

	}

	/**
	 * Return port name
	 * 
	 * @param configuration
	 * @return
	 */
	public static final String getPort(Configuration configuration) {
		String retVal = configuration.getString(SERIAL_PORT_NAME, null);

		if (retVal == null) {
			throw new IllegalArgumentException("Null value!");
		}

		return retVal;
	}

	/**
	 * Return port time out
	 * 
	 * @param configuration
	 * @return
	 */
	public static final Integer getPortTimeOut(Configuration configuration) {
		Integer retVal = configuration.getInteger(TIME_OUT, null);

		if (retVal == null) {
			throw new IllegalArgumentException("Null value!");
		}

		return retVal;
	}

	/**
	 * Return port bitrate
	 * 
	 * @param configuration
	 * @return
	 */
	public static Integer getBitrate(Configuration configuration) {
		Integer retVal = configuration.getInteger(BITRATE, null);

		if (retVal == null) {
			throw new IllegalArgumentException("Null value!");
		}

		return retVal;
	}
}
