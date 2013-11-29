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

	public Long getValueMeasured();

	public void setValueMeasured(Long valueMeasured);

	public Long getValueTransformed();

	public void setValueTransformed(Long valueTransformed);

	public java.util.Date getLogDate();

	public void setLogDate(java.util.Date logDate);

}
