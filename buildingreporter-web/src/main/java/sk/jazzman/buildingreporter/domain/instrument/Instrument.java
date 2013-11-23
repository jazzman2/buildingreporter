package sk.jazzman.buildingreporter.domain.instrument;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.instrument.InstrumentInf;

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
}
