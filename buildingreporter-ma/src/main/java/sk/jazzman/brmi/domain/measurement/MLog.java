/**
 * 
 */
package sk.jazzman.brmi.domain.measurement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

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
public class MLog extends MAEntityAbt implements MLogArduinoInf {
	/** serial id */
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@XStreamAlias("valueMeasured")
	@Column(name = "value_measured")
	private Long valueMeasured;

	@XStreamAlias("valueTransformed")
	@Column(name = "value_transformed")
	private Long valueTransformed;

	@XStreamAlias("logDate")
	@Column(name = "log_date", nullable = false)
	private java.util.Date logDate;

	@XStreamAlias("instrumentName")
	@Column(name = "instrument_name", nullable = false, columnDefinition = "TEXT")
	@Length(max = 50)
	private String instrumentName;

	@XStreamAlias("unitMeasured")
	@Column(name = "unit_measured", nullable = false, columnDefinition = "TEXT")
	@Length(max = 50)
	private String unitMeasured;

	@XStreamAlias("unitTransformed")
	@Column(name = "unit_transformed", columnDefinition = "TEXT")
	@Length(max = 50)
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
	@Override
	public String getUnitMeasured() {
		return unitMeasured;
	}

	/**
	 * setter unit measured
	 * 
	 * @param unitMeasured
	 */
	@Override
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
