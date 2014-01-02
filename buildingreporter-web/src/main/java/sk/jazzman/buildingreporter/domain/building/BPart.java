package sk.jazzman.buildingreporter.domain.building;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@SequenceGenerator(name = "SEQ", sequenceName = "bpart_seq")
public class BPart {

	/**
     */
	@Id
	@NotNull
	@Column(unique = true, name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

	/**
     */
	@NotNull
	@Size(min = 2)
	private String name;

	/**
     */
	@ManyToOne
	private BPart parent;

	/**
     */
	@NotNull
	@ManyToOne
	private BType type;
}
