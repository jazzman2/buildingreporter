/**
 * 
 */
package sk.jazman.brmi.core;

/**
 * @author jano
 * 
 */
public interface CoreEventManagerInf {

	/**
	 * Fire {@link CoreEventInf}
	 * 
	 * @param coreEvent
	 */
	public void fireEvent(CoreEventInf coreEvent);

	/**
	 * Register {@link CoreEventHandlerInf}
	 * 
	 * @param name
	 */
	public void registerHandler(String name, CoreEventHandlerInf handler);
}
