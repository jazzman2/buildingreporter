package sk.jazzman.buildingreporter.domain.instrument;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


public class AParameter implements AParameterInf {
	/** Serial id */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
	private String paramName;
	
	private String paramValue;
	
	@NotNull
	@ManyToOne
	private Instrument instrument;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
