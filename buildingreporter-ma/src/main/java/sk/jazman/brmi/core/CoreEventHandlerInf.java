/**
 * 
 */
package sk.jazman.brmi.core;

/**
 * Core Event Handler
 * 
 * @author jano
 * 
 */
public interface CoreEventHandlerInf {

	/**
	 * Handle Event
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void handle(CoreEventInf event) throws Exception;

}
