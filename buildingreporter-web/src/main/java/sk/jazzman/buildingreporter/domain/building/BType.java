package sk.jazzman.buildingreporter.domain.building;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.BREntityInf;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@SequenceGenerator(name = "SEQ", sequenceName = "btype_seq")
public class BType implements BREntityInf {

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
}
