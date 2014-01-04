/**
 * 
 */
package sk.jazzman.buildingreporter.domain.measurement;

import sk.jazzman.buildingreporter.domain.BREntityInf;

/**
 * Measusement Log -
 * 
 * @author jano
 * 
 */
public interface MLogInf extends BREntityInf {

	public Double getValueMeasured();

	public void setValueMeasured(Double valueMeasured);

	public Double getValueTransformed();

	public void setValueTransformed(Double valueTransformed);

	public java.util.Date getLogDate();

	public void setLogDate(java.util.Date logDate);

}
