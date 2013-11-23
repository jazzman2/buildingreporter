package sk.jazzman.buildingreporter.server;

/**
 * Server Action Register
 * 
 * @author jkovalci
 * 
 */
public interface ServerActionRegisterInf {

	/**
	 * Register Action
	 * 
	 * @param name
	 * @param action
	 */
	public void registerAction(String name, ServerActionInf action);

	/**
	 * Get Action
	 * 
	 * @param name
	 * @return
	 */
	public ServerActionInf getAction(String name);
}
