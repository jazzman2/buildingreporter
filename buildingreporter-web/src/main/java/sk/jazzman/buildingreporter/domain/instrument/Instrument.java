package sk.jazzman.buildingreporter.domain.instrument;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.configuration.Configuration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import sk.jazzman.buildingreporter.domain.building.BPart;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Instrument implements InstrumentInf {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(unique = true)
	@Size(min = 2)
	private String name;

	@NotNull
	@ManyToOne
	private BPart part;

	/**
     */
	@NotNull
	@Column(unique = true)
	private Long id;

	/**
	 * Create {@link Criteria}
	 * 
	 * @return
	 */
	public static Criteria createCriteria() {
		return entityManager().unwrap(Session.class).createCriteria(Instrument.class);
	}

	@Transactional
	public static InstrumentInf register(Configuration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		String name = InstrumentUtils.getName(configuration);

		Instrument i = (Instrument) createCriteria().add(Restrictions.eq("name", name)).uniqueResult();
		InstrumentInf retVal;
		if (i == null) {
			i.persist();
		}

		retVal = i;

		return retVal;
	}
}
