/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.DefaultActionHandlerAbt;
import sk.jazzman.brmi.core.CoreConfigurationHelper;
import sk.jazzman.brmi.core.CoreEvent;
import sk.jazzman.brmi.core.CoreEventHandlerAbt;
import sk.jazzman.brmi.core.CoreEventHandlerInf;
import sk.jazzman.brmi.core.CoreEventInf;
import sk.jazzman.brmi.core.CoreEventResolverInf;
import sk.jazzman.brmi.domain.measurement.MLogArduinoInf;
import sk.jazzman.brmi.domain.measurement.MLogNotSend;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;
import sk.jazzman.buildingreporter.domain.utils.ActionParamGetter;

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
		actionRegister.registerAll();

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
				register(CoreConfigurationHelper.EVENT_MLOG_READ, new CoreEventResolverInf() {
					@Override
					public void resolve(CoreEventInf event) throws Exception {
						Map<String, Object> params = (Map<String, Object>) event.getParameters();

						Object value = ActionParamGetter.get("value", Object.class, params);

						if (value instanceof MLogArduinoInf) {
							MLogArduinoInf mlog;
							try {
								mlog = (MLogArduinoInf) perform("PUT-mlog",//
										new ActionParamBuilder().put("mlog", value).build()).get("mlog");

							} catch (Exception ex) {
								mlog = null;
								getLogger().error("MLog - put unsucessful", ex);
								getSandbox().getCoreEventManager().fireEvent(
										new CoreEvent(CoreConfigurationHelper.EVENT_MLOG_PUT_UNSUCCESS, new ActionParamBuilder().put("mlog", value).build(), JPAActionHandler.class));
							}

							if (mlog != null) {
								getSandbox().getCoreEventManager().fireEvent(
										new CoreEvent(CoreConfigurationHelper.EVENT_MLOG_PUT, new ActionParamBuilder().put("mlog", mlog).build(), JPAActionHandler.class));
							}
						} else {
							throw new IllegalStateException("Wrong object!");
						}
					}
				});

				register(CoreConfigurationHelper.EVENT_MLOG_SEND_UNSUCCESS, new CoreEventResolverInf() {

					@Override
					public void resolve(CoreEventInf event) throws Exception {
						Map<String, Object> params = (Map<String, Object>) event.getParameters();

						Object value = ActionParamGetter.get("value", Object.class, params);

						if (value instanceof MLogArduinoInf) {
							MLogNotSend ns = new MLogNotSend();
							ns.setMlog(((MLogArduinoInf) value).getId());

							try {
								MLogNotSend mlogNS = (MLogNotSend) perform("PUT-mlog_not_send",//
										new ActionParamBuilder().put("value", ns).build()).get("value");
								getSandbox().getCoreEventManager().fireEvent(
										new CoreEvent(CoreConfigurationHelper.EVENT_MLOGNOTSEND_PUT, new ActionParamBuilder().put("value", mlogNS).build(), JPAActionHandler.class));
							} catch (Exception ex) {
								getLogger().error("MLogNotSend - put unsucessful", ex);
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
			JPAActionInf action = getActionRegister().get(actionName);

			Session session = getSessionManager().getSession();

			Map<String, Object> systemParams = getSystemParams();

			try {
				retVal = perform(action, actionParams, systemParams, session);
				getLogger().info("Action '" + actionName + "' has been performed.");
			} catch (Exception e) {
				retVal = null;
				getLogger().error("Action '" + actionName + "' has not been performed. Do rollback.", e);
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
