/**
 * 
 */
package sk.jazzman.buildingreporter.domain.measurement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Measured Log
 * 
 * @author jano
 * 
 */
@Entity
@Table(name = "mlog")
@SequenceGenerator(name = "SEQ", sequenceName = "log_seq")
public class MLog extends MAEntity implements MLogInf {
	/** serial id */
	private static final long serialVersionUID = 1L;

	@Column(name = "value_measured")
	private Long valueMeasured;

	@Column(name = "value_transformed")
	private Long valueTransformed;

	@Column(name = "log_date", nullable = false)
	private java.util.Date logDate;

	@Column(name = "instrument_name", nullable = false)
	private String instrumentName;

	@Column(name = "unit_measured", nullable = false)
	private String unitMeasured;

	@Column(name = "unit_transformed")
	private String unitTransformed;

	@Override
	public Long getValueMeasured() {
		return valueMeasured;
	}

	@Override
	public void setValueMeasured(Long valueMeasured) {
		this.valueMeasured = valueMeasured;
	}

	@Override
	public Long getValueTransformed() {
		return valueTransformed;
	}

	@Override
	public void setValueTransformed(Long valueTransformed) {
		this.valueTransformed = valueTransformed;
	}

	@Override
	public Date getLogDate() {
		return logDate;
	}

	@Override
	public void setLogDate(Date date) {
		this.logDate = date;
	}

	/**
	 * Getter instrument name
	 * 
	 * @return
	 */
	public String getInstrumentName() {
		return instrumentName;
	}

	/**
	 * setter instrument name
	 * 
	 * @param instrumentName
	 */
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	/**
	 * getter unit measured
	 * 
	 * @return
	 */
	public String getUnitMeasured() {
		return unitMeasured;
	}

	/**
	 * setter unit measured
	 * 
	 * @param unitMeasured
	 */
	public void setUnitMeasured(String unitMeasured) {
		this.unitMeasured = unitMeasured;
	}

	/**
	 * getter transformed unit
	 * 
	 * @return
	 */
	public String getUnitTransformed() {
		return unitTransformed;
	}

	/**
	 * setter unit transformed
	 * 
	 * @param unitTransformed
	 */
	public void setUnitTransformed(String unitTransformed) {
		this.unitTransformed = unitTransformed;
	}

}
