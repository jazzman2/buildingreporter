/**
 * 
 */
package sk.jazzman.buildingreporter.domain.measurement;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * @author jano
 * 
 */
@RooJavaBean
// @RooJpaEntity
// @BatchSize(size = 1000)
// @Polymorphism(type = PolymorphismType.EXPLICIT)
// @Transactional(readOnly = true)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Subselect(value = "SELECT * FROM mlog")
// @Lazy(value = false)
// @Immutable
public class LogReport implements Serializable {

	/** serial id */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Double averageValue;

	@NotNull
	@DateTimeFormat(style = "M-")
	private java.sql.Timestamp logDate;

	@Column(name = "unit_transformed")
	private String unit;

	@NotNull
	private Double diff;

	/**
	 * {@link Constructor}
	 */
	public LogReport() {

	}

	/**
	 * {@link Constructor}
	 * 
	 * @param averageValue
	 * @param logDate
	 * @param unit
	 * @param diff
	 */
	public LogReport(Double averageValue, Date logDate, String unit, Double diff) {
		this.averageValue = averageValue;
		this.logDate = logDate != null ? new Timestamp(logDate.getTime()) : null;
		this.unit = unit;
		this.diff = diff;
	}

}
