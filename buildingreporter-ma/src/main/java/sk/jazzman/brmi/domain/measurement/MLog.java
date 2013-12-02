/**
 * 
 */
package sk.jazzman.brmi.domain.measurement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import sk.jazzman.buildingreporter.domain.measurement.MLogInf;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Measured Log
 * 
 * @author jano
 * 
 */
@Entity
@Table(name = "mlog")
@SequenceGenerator(name = "SEQ", sequenceName = "log_seq")
@XStreamAlias("mlog")
public class MLog extends MAEntity implements MLogInf {
	/** serial id */
	private static final long serialVersionUID = 1L;

	@XStreamAlias("valueMeasured")
	private Long valueMeasured;

	@XStreamAlias("valueTransformed")
	private Long valueTransformed;

	@XStreamAlias("logDate")
	private java.util.Date logDate;

	@XStreamAlias("instrumentName")
	private String instrumentName;

	@XStreamAlias("unitMeasured")
	private String unitMeasured;

	@XStreamAlias("unitTransformed")
	private String unitTransformed;

	@Column(name = "value_measured")
	@Override
	public Long getValueMeasured() {
		return valueMeasured;
	}

	@Override
	public void setValueMeasured(Long valueMeasured) {
		this.valueMeasured = valueMeasured;
	}

	@Column(name = "value_transformed")
	@Override
	public Long getValueTransformed() {
		return valueTransformed;
	}

	@Override
	public void setValueTransformed(Long valueTransformed) {
		this.valueTransformed = valueTransformed;
	}

	@Column(name = "log_date", nullable = false)
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
	@Column(name = "instrument_name", nullable = false, columnDefinition = "TEXT")
	@Lob
	@Length(max = 255)
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
	@Column(name = "unit_measured", nullable = false, columnDefinition = "TEXT")
	@Length(max = 255)
	@Lob
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
	@Column(name = "unit_transformed", columnDefinition = "TEXT")
	@Length(max = 255)
	@Lob
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
