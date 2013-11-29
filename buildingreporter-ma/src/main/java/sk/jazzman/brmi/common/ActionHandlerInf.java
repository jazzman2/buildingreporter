/**
 * 
 */
package sk.jazzman.brmi.common;

import java.util.Map;

/**
 * Server Action Handler
 * 
 * @author jkovalci
 * 
 */
public interface ActionHandlerInf<A extends ActionInf> {

	/**
	 * Action Register
	 * 
	 * @return
	 */
	public ActionRegisterInf<A> getActionRegister();

	/**
	 * Return configuration of action handler
	 * 
	 * @return
	 */
	public Map<String, Object> getConfiguration();

	/**
	 * Call Action
	 * 
	 * @param actionName
	 * @param actionParams
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception;

	/**
	 * Initialize Action Handler
	 * 
	 * @param configuration
	 * 
	 * @throws Exception
	 */
	public void init(Map<String, Object> configuration) throws Exception;
}
