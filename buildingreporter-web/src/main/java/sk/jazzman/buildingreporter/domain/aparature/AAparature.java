package sk.jazzman.buildingreporter.domain.aparature;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.building.BPart;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AAparature implements AAparatureInf {
	/** Serial id */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(unique = true)
	@Id
	private Long id;

	@NotNull
	@Column(unique = true)
	@Size(min = 2)
	private String name;

	@NotNull
	@ManyToOne
	private BPart part;

}
