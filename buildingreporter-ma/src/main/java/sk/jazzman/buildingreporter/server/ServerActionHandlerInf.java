/**
 * 
 */
package sk.jazzman.buildingreporter.server;

import java.util.Map;

/**
 * Server Action Handler
 * 
 * @author jkovalci
 * 
 */
public interface ServerActionHandlerInf {

	/**
	 * Action Register
	 * 
	 * @return
	 */
	public ServerActionRegisterInf getActionRegister();

	/**
	 * Return configuration of action handler
	 * 
	 * @return
	 */
	public Map<String, Object> getConfiguration();

	/**
	 * Call Action On Server
	 * 
	 * @param actionName
	 * @param actionParams
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> call(String actionName, Map<String, Object> actionParams) throws Exception;
}
