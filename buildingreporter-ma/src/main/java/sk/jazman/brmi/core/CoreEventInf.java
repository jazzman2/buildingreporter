/**
 * 
 */
package sk.jazman.brmi.core;

/**
 * Core Even
 * 
 * @author jano
 * 
 */
public interface CoreEventInf {

	/**
	 * Core Event Name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Core Event Parameters
	 * 
	 * @return
	 */
	public Object getParameters();

	/**
	 * Return source of event
	 * 
	 * @return
	 */
	public Object getSource();
}
