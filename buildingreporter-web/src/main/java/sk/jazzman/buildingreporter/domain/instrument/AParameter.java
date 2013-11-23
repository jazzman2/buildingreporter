package sk.jazzman.buildingreporter.domain.instrument;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sk.jazzman.buildingreporter.domain.instrument.AParameterInf;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AParameter implements AParameterInf {
	/** Serial id */
	private static final long serialVersionUID = 1L;

	
	
	@NotNull
	private String paramName;

	private String paramValue;

	@NotNull
	@ManyToOne
	private Instrument instrument;
}
