package sk.jazzman.buildingreporter.domain.measurement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.instrument.Instrument;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@XStreamAlias("mlog")
public class MLog implements MLogInf {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(unique = true)
	private Long id;

	@NotNull
	private Double valueMeasured;

	private Double valueTransformed;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date logDate;

	@ManyToOne
	private MUnit unitTransformed;

	@NotNull
	@ManyToOne
	private MUnit unitMeasured;

	@NotNull
	@ManyToOne
	private Instrument instrument;

	@NotNull
	@ManyToOne
	private BPart item;

	/**
	 * Create {@link Criteria}
	 * 
	 * @return
	 */
	public static Criteria createCriteria() {
		return entityManager().unwrap(Session.class).createCriteria(MLog.class);
	}
}
