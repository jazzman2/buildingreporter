package sk.jazzman.buildingreporter.domain.building;

import sk.jazzman.buildingreporter.domain.BREntityInf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@RooJavaBean
//@RooToString
//@RooJpaActiveRecord
//@SequenceGenerator(name = "SEQ", sequenceName = "bpart_seq")
@Entity
public class BPart implements BREntityInf {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
