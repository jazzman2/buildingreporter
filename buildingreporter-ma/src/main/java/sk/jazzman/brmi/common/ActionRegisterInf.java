package sk.jazzman.brmi.common;


/**
 * Server Action Register
 * 
 * @author jkovalci
 * 
 */
public interface ActionRegisterInf<A extends ActionInf> {

	/**
	 * Register Action
	 * 
	 * @param name
	 * @param action
	 */
	public void registerAction(String name, A action);

	/**
	 * Get Action
	 * 
	 * @param name
	 * @return
	 */
	public A getAction(String name);

	/**
	 * Register Actions
	 * 
	 */
	public void registerActions() throws Exception;
}
