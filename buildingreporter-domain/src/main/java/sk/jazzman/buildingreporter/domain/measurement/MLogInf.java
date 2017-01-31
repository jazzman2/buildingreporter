/**
 *
 */
package sk.jazzman.buildingreporter.domain.measurement;

import sk.jazzman.buildingreporter.domain.BREntityInf;

import java.sql.Timestamp;

/**
 * Measusement Log -
 *
 * @author jano
 */
public interface MLogInf extends BREntityInf {
	
	public Double getValueMeasured();
	
	public void setValueMeasured(Double valueMeasured);
	
	public Double getValueTransformed();
	
	public void setValueTransformed(Double valueTransformed);
	
	public Timestamp getLogDate();
	
	public void setLogDate(Timestamp logDate);
	
}
