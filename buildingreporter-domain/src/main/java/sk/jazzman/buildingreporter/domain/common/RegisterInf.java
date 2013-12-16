package sk.jazzman.buildingreporter.domain.common;

/**
 * Common Register
 * 
 * @author jano
 * 
 */
public interface RegisterInf<T extends Object> {
	/**
	 * Register Object
	 * 
	 * @param name
	 * @param action
	 */
	public void register(String name, T object);

	/**
	 * Get registered object
	 * 
	 * @param name
	 * @return
	 */
	public T get(String name);

	/**
	 * Register all objects
	 * 
	 */
	public void registerAll() throws Exception;

	/**
	 * Unregister object
	 * 
	 * @param name
	 * @return
	 */
	public T unregister(String name);

	/**
	 * Unregister all objects
	 */
	public void unregisterAll();

}
