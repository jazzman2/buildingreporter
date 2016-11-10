package sk.jazzman.buildingreporter.domain.instrument;

import org.apache.commons.configuration2.Configuration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.building.BuildingConfigurationHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SequenceGenerator(name = "SEQ", sequenceName = "instrument_seq")
@Entity
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
	@Id
	@NotNull
	@Column(unique = true, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
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
		if(configuration == null) {
			throw new IllegalArgumentException("Null argument!");
		}
		
		String name = InstrumentUtils.getName(configuration);
		
		Instrument i = (Instrument) createCriteria().add(Restrictions.eq("name", name)).uniqueResult();
		InstrumentInf retVal;
		if(i == null) {
			i = new Instrument();
			i.setName(name);
			i.setPart(BuildingConfigurationHelper.getUndefinedBuilding(configuration));
			i.persist();
		}
		
		retVal = i;
		
		return retVal;
	}
}
