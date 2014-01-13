/**
 * 
 */
package sk.jazzman.buildingreporter.domain.measurement;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.Subselect;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jano
 * 
 */
// @RooJavaBean
@RooJpaEntity
// @BatchSize(size = 1000)
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Transactional(readOnly = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Subselect(value = "SELECT * FROM mlog")
@Lazy(value = false)
@Immutable
public class MLogReport implements MLogInf {
	/** serial id */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	transient EntityManager entityManager;

	public static final EntityManager entityManager() {
		EntityManager em = new MLog().entityManager;
		if (em == null)
			throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Id
	@NotNull
	@Column(unique = true)
	private Long id;

	@NotNull
	private Double valueMeasured;

	private Double valueTransformed;

	@NotNull
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private java.sql.Timestamp logDate;

	@Column(name = "unit_transformed")
	private String unitTransformed;

	@NotNull
	@Column(name = "unit_measured")
	private String unitMeasured;

	@NotNull
	@Column(name = "instrument")
	private Long instrument;

	@NotNull
	@Column(name = "item")
	private Long item;

	/**
	 * Create {@link Criteria}
	 * 
	 * @return
	 */
	public static Criteria createCriteria() {
		return entityManager().unwrap(Session.class).createCriteria(MLogReport.class);
	}

	public MLogReport() {

	}

	public MLogReport(Long id, Double valueMeasured, Double valueTransformed, Date logDate, String unitMeasured, String unitTransformed, Long instrument, Long item) {
		this.id = id;
		this.valueMeasured = valueMeasured;
		this.valueTransformed = valueTransformed;
		this.logDate = logDate != null ? new Timestamp(logDate.getTime()) : null;
		this.unitMeasured = unitMeasured;
		this.unitTransformed = unitTransformed;
		this.instrument = instrument;
		this.item = item;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Date getLogDate() {
		return logDate;
	}

	@Override
	public void setLogDate(Date logDate) {
		this.logDate = logDate != null ? new Timestamp(logDate.getTime()) : null;
	}

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
}
