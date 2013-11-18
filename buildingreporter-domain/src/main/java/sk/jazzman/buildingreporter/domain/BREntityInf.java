/**
 * 
 */
package sk.jazzman.buildingreporter.domain;

import java.io.Serializable;

/**
 * Default Entity Object in project
 * 
 * @author jkovalci
 * 
 */
public interface BREntityInf extends Serializable {

	/**
	 * Getter id
	 * 
	 * @return
	 */
	public Long getId();

	/**
	 * Setter id
	 * 
	 * @param id
	 */
	public void setId(Long id);
}
