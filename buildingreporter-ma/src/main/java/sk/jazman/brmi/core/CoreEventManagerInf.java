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
	 * 
	 * @throws Exception
	 */
	public void fireEvent(CoreEventInf coreEvent) throws Exception;

	/**
	 * Register {@link CoreEventHandlerInf}
	 * 
	 * @param name
	 */
	public void register(String name, CoreEventHandlerInf handler);

	/**
	 * Unregister {@link CoreEventHandlerInf}
	 * 
	 * @param name
	 */
	public void unregister(String name);
}
