/**
 *
 */
package sk.jazzman.brmi.domain.measurement;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;


/**
 * Measured Log
 *
 * @author jano
 */
@Entity
@Table(name = "mlog")
@SequenceGenerator(name = "SEQ", sequenceName = "log_seq")
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
	
	@Column(name = "value_measured")
	private Double valueMeasured;
	
	@Column(name = "value_transformed")
	private Double valueTransformed;
	
	@Column(name = "log_date", nullable = false)
	private Timestamp logDate;
	
	@Column(name = "instrument_name", nullable = false, columnDefinition = "TEXT")
	@Length(max = 50)
	private String instrumentName;
	
	@Column(name = "unit_measured", nullable = false, columnDefinition = "TEXT")
	@Length(max = 50)
	private String unitMeasured;
	
	@Column(name = "unit_transformed", columnDefinition = "TEXT")
	@Length(max = 50)
	private String unitTransformed;
	
	@Column(name = "sensor_id", columnDefinition = "TEXT")
	@Length(max = 50)
	private String sensorId;
	
	@Override
	public Double getValueMeasured() {
		return valueMeasured;
	}
	
	@Override
	public void setValueMeasured(Double valueMeasured) {
		this.valueMeasured = valueMeasured;
	}
	
	@Override
	public Double getValueTransformed() {
		return valueTransformed;
	}
	
	@Override
	public void setValueTransformed(Double valueTransformed) {
		this.valueTransformed = valueTransformed;
	}
	
	@Override
	public Timestamp getLogDate() {
		return logDate;
	}
	
	@Override
	public void setLogDate(Timestamp date) {
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
	
	/**
	 * Getter sensor id
	 *
	 * @return
	 */
	@Override
	public String getSensorId() {
		return sensorId;
	}
	
	/**
	 * Setter sensor id
	 *
	 * @param sensorId
	 */
	@Override
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
}
