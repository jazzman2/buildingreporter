/**
 * 
 */
package sk.jazzman.brmi.domain.measurement;

import sk.jazzman.buildingreporter.domain.measurement.MLogInf;

/**
 * @author jano
 * 
 */
public interface MLogArduinoInf extends MLogInf {

	/**
	 * Setter Unit Measured
	 * 
	 * @param string
	 */
	public void setUnitMeasured(String string);

	/**
	 * Getter Unit Measured
	 * 
	 * @return
	 */
	public String getUnitMeasured();

	public void setSensorId(String sensorId);

	public String getSensorId();
}
