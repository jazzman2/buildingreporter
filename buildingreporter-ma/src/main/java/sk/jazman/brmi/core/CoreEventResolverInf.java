/**
 * 
 */
package sk.jazman.brmi.core;

/**
 * @author jkovalci
 * 
 */
public interface CoreEventResolverInf {

	/**
	 * Resolve core event
	 * 
	 * @param event
	 * 
	 * @throws Exception
	 */
	public void resolve(CoreEventInf event) throws Exception;
}
