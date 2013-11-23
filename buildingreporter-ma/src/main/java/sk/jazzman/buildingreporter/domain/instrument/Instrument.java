package sk.jazzman.buildingreporter.domain.instrument;

import sk.jazzman.buildingreporter.domain.instrument.InstrumentInf;

/**
 * Aparature
 * 
 * @author jkovalci
 * 
 */
public class Instrument implements InstrumentInf {
	/** Serial id */
	private static final long serialVersionUID = 1L;
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
