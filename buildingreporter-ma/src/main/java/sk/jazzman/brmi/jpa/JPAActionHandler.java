/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.common.DefaultActionHandlerAbt;

/**
 * JPA Action Handler
 * 
 * @author jano
 * 
 */
public class JPAActionHandler extends DefaultActionHandlerAbt<JPAActionInf> {

	private static final Logger logger = LoggerFactory.getLogger(JPAActionHandler.class);

	private SessionManager sessionManager;

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	@Override
	public void init(Map<String, Object> configuration) throws Exception {
		getLogger().debug("Init JPAActionHandler ... ");

		super.init(configuration);

		actionRegister = new JPAActionRegister();
		actionRegister.registerActions();

		sessionManager = new SessionManager();

		getLogger().debug("Init JPAActionHandler ... Done ");
	}

	/**
	 * Return {@link SessionManager}
	 * 
	 * @return
	 */
	private SessionManager getSessionManager() {
		return sessionManager;
	}

	@Override
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception {
		getLogger().info("Perform action '" + actionName + "'.");

		JPAActionInf action = getActionRegister().getAction(actionName);

		Session session = getSessionManager().getSession();

		Map<String, Object> systemParams = getSystemParams();

		actionParams = action.doPreAction(actionParams, systemParams, session);

		actionParams = action.doAction(actionParams, systemParams, session);

		actionParams = action.doPostAction(actionParams, systemParams, session);

		getLogger().info("Action '" + actionName + "' has been performed.");

		return actionParams;
	}

	/**
	 * Return system params
	 * 
	 * @return
	 */
	private Map<String, Object> getSystemParams() {
		Map<String, Object> retVal = new HashMap<String, Object>();

		return retVal;
	}
}
