package sk.jazzman.buildingreporter.domain.measurement;

import sk.jazzman.buildingreporter.domain.BREntityInf;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//@RooJavaBean
//@RooToString
//@RooJpaActiveRecord
public class MUnit implements BREntityInf {
	
	/** serial id */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(unique = true)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String name_en;
	
	@NotNull
	private String symbol;
	
	@NotNull
	private Integer multiple;
}
