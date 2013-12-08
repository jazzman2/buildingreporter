/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazman.brmi.core.CoreConfigurationHelper;
import sk.jazman.brmi.core.CoreEvent;
import sk.jazman.brmi.core.CoreEventHandlerAbt;
import sk.jazman.brmi.core.CoreEventHandlerInf;
import sk.jazman.brmi.core.CoreEventInf;
import sk.jazman.brmi.core.CoreEventResolverInf;
import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.DefaultActionHandlerAbt;
import sk.jazzman.brmi.common.ParameterBuilder;
import sk.jazzman.brmi.domain.measurement.MLogArduinoInf;

/**
 * JPA Action Handler
 * 
 * @author jano
 * 
 */
public class JPAActionHandler extends DefaultActionHandlerAbt<JPAActionInf> {

	private static final Logger logger = LoggerFactory.getLogger(JPAActionHandler.class);

	private SessionManager sessionManager;
	private CoreEventHandlerInf coreEventHandler;

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	@Override
	public void init(SandboxInf sandbox) throws Exception {
		getLogger().debug("Init JPAActionHandler ... ");

		super.init(sandbox);

		actionRegister = new JPAActionRegister();
		actionRegister.registerActions();

		sessionManager = new SessionManager();

		initCoreEventHandler(sandbox);

		getLogger().debug("Init JPAActionHandler ... Done ");
	}

	/**
	 * Init core event handler
	 * 
	 * @param sandbox
	 */
	private void initCoreEventHandler(SandboxInf sandbox) {
		coreEventHandler = new CoreEventHandlerAbt() {
			@Override
			public void registerResolvers() {
				register(CoreConfigurationHelper.EVENT_ARDUINO_TEMTERATURE_READ, new CoreEventResolverInf() {
					@Override
					public void resolve(CoreEventInf event) throws Exception {
						Map<String, Object> params = (Map<String, Object>) event.getParameters();

						Object value = params.get("value");

						if (value instanceof MLogArduinoInf) {
							try {
								MLogArduinoInf mlog = (MLogArduinoInf) perform("PUT-mlog", new ParameterBuilder().setParameter("mlog", value).build()).get("mlog");
								getSandbox().getCoreEventManager().fireEvent(
										new CoreEvent(CoreConfigurationHelper.EVENT_MLOG_PUT, new ParameterBuilder().setParameter("mlog", mlog).build(), JPAActionHandler.class));
							} catch (Exception ex) {
								getLogger().error("MLog - put unsucessful", ex);
								getSandbox().getCoreEventManager().fireEvent(
										new CoreEvent(CoreConfigurationHelper.EVENT_MLOG_PUT_UNSUCCESS, new ParameterBuilder().setParameter("mlog", value).build(), JPAActionHandler.class));
							}
						} else {
							throw new IllegalStateException("Wrong object!");
						}
					}
				});
			}
		};

		sandbox.getCoreEventManager().register("JPACoreEventHandler", coreEventHandler);
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

		Map<String, Object> retVal;

		if (!getSessionManager().isInitialized()) {
			getLogger().error("Session mamager not initialized yet!");
			retVal = null;
		} else {
			JPAActionInf action = getActionRegister().getAction(actionName);

			Session session = getSessionManager().getSession();

			Map<String, Object> systemParams = getSystemParams();

			try {
				retVal = perform(action, actionParams, systemParams, session);
				getLogger().info("Action '" + actionName + "' has been performed.");
			} catch (Exception e) {
				retVal = null;
				getLogger().info("Action '" + actionName + "' has not been performed. Do rollback.", e);
				session.getTransaction().rollback();
			}
		}
		return retVal;
	}

	/**
	 * Perform Action
	 * 
	 * @param action
	 * @param actionParams
	 * @param systemParams
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> perform(JPAActionInf action, Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		actionParams = action.doPreAction(actionParams, systemParams, session);

		actionParams = action.doAction(actionParams, systemParams, session);

		actionParams = action.doPostAction(actionParams, systemParams, session);

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
