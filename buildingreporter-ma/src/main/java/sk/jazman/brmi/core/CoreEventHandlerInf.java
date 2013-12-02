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

	/**
	 * Register resolvers
	 */
	public void registerResolvers();

	/**
	 * Register {@link CoreEventResolverInf}
	 * 
	 * @param name
	 * @param resolver
	 */
	public void register(String name, CoreEventResolverInf resolver);

	/**
	 * Unregister {@link CoreEventResolverInf}
	 * 
	 * @param name
	 * @return
	 */
	public CoreEventResolverInf unregister(String name);
}
