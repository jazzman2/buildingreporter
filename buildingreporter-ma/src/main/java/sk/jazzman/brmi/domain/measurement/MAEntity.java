/**
 * 
 */
package sk.jazzman.brmi.domain.measurement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import sk.jazzman.buildingreporter.domain.BREntityInf;

/**
 * @author jano
 * 
 */
public class MAEntity implements BREntityInf {
	/** serial id */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
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
