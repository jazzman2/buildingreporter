package sk.jazzman.buildingreporter.domain.measurement;

import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.joda.time.DateTime;
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
@SequenceGenerator(name = "SEQ", sequenceName = "mlog_seq")
@BatchSize(size = 20)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class MLog implements MLogInf {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
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
	 * {@link Constructor}
	 */
	public MLog() {

	}

	/**
	 * {@link Constructor}
	 * 
	 * @param id
	 * @param valueMeasured
	 * @param valueTransformed
	 * @param logDate
	 * @param unitMeasured
	 * @param unitTransformed
	 * @param instrument
	 * @param item
	 */
	public MLog(Long id, Double valueMeasured, Double valueTransformed, Date logDate, MUnit unitMeasured, MUnit unitTransformed, Instrument instrument, BPart item) {
		this.id = id;
		this.valueMeasured = valueMeasured;
		this.valueTransformed = valueTransformed;
		this.logDate = logDate;
		this.unitMeasured = unitMeasured;
		this.unitTransformed = unitTransformed;
		this.instrument = instrument;
		this.item = item;
	}

	/**
	 * Create {@link Criteria}
	 * 
	 * @return
	 */
	public static Criteria createCriteria() {
		return entityManager().unwrap(Session.class).createCriteria(MLog.class);
	}

	public static Long getNextValSeq() {
		Query q = entityManager().unwrap(Session.class).createSQLQuery("select MYSEQ.nextval as num from dual").addScalar("num", StandardBasicTypes.BIG_INTEGER);

		return ((BigInteger) q.uniqueResult()).longValue();
	}

	public static List<MLog> findLogsForHour(Date date, Long itemId) {
		return MLog.createCriteria()//
				.add(Restrictions.eq("item.id", itemId))//
				// .add(Restrictions.eq("item", itemId))//
				.addOrder(Order.asc("logDate"))//
				.add(Restrictions.ge("logDate", new Timestamp(date.getTime())))//
				.add(Restrictions.le("logDate", new Timestamp(new DateTime(date).plusHours(1).getMillis())))
				// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)//
				.list();
	}
}
