/**
 * 
 */
package sk.jazzman.brmi.server.ws;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazman.brmi.core.CoreConfigurationHelper;
import sk.jazman.brmi.core.CoreEventHandlerAbt;
import sk.jazman.brmi.core.CoreEventHandlerInf;
import sk.jazman.brmi.core.CoreEventInf;
import sk.jazman.brmi.core.CoreEventResolverInf;
import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.DefaultActionHandlerAbt;
import sk.jazzman.brmi.common.ParameterBuilder;
import sk.jazzman.brmi.domain.measurement.MLog;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ServerConfigurationHelper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Web Service Server Action Handler
 * 
 * @author jano
 * 
 */
public class WSServerActionHandler extends DefaultActionHandlerAbt<ServerActionInf> {

	private static final Logger logger = LoggerFactory.getLogger(WSServerActionHandler.class);

	private CoreEventHandlerInf coreEventHandler;
	private Client client;

	/**
	 * {@link Constructor}
	 * 
	 * 
	 */
	public WSServerActionHandler() {

	}

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Return REST Client
	 * 
	 * @return
	 */
	private Client getClient() {
		return client;
	}

	/**
	 * init action handler
	 * 
	 * @param configuration
	 * 
	 */
	@Override
	public void init(SandboxInf sandbox) throws Exception {
		super.init(sandbox);

		actionRegister = new WSActionRegister();
		actionRegister.registerActions();

		ClientConfig config = new DefaultClientConfig();

		client = Client.create(config);

		initCoreEventHandler();
	}

	/**
	 * Init core event handler
	 */
	private void initCoreEventHandler() {
		coreEventHandler = new CoreEventHandlerAbt() {
			@Override
			public void registerResolvers() {
				register(CoreConfigurationHelper.EVENT_MLOG_PUT, new CoreEventResolverInf() {
					@Override
					public void resolve(CoreEventInf event) throws Exception {
						Map<String, Object> params = (Map<String, Object>) event.getParameters();

						Object value = params.get("value");

						if (value instanceof MLog) {
							try {
								perform("/log", new ParameterBuilder().setParameter("mlog", value).build());
							} catch (Exception ex) {
								getLogger().error("Could not to sent to server!");
								// FIXME:
							}
						} else {
							throw new UnsupportedDataTypeException("Not supported data type!");
						}
					}
				});

				register(CoreConfigurationHelper.EVENT_MLOG_PUT_UNSUCCESS, new CoreEventResolverInf() {
					@Override
					public void resolve(CoreEventInf event) throws Exception {
						Map<String, Object> params = (Map<String, Object>) event.getParameters();

						Object value = params.get("value");

						if (value instanceof MLog) {
							MLog log = (MLog) value;

							// FIXME: set id
							// log.setId(id);
							try {
								perform("/log", new ParameterBuilder().setParameter("mlog", value).build());
							} catch (Exception ex) {
								getLogger().error("Could not to sent to server!");
							}
						} else {
							throw new UnsupportedDataTypeException("Not supported data type!");
						}
					}
				});
			}
		};
	}

	@Override
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception {
		RESTServerActionInf action = (RESTServerActionInf) getActionRegister().getAction(actionName);

		Map<String, Object> systemParams = createSystemParams();

		ClientResponse response = action.performRequest(getClient(), actionParams, systemParams, getSandbox());

		return action.handleResponse(response);
	}

	/**
	 * Build System Parameters
	 * 
	 * @return
	 */
	protected Map<String, Object> createSystemParams() {
		Map<String, Object> retVal = new HashMap<String, Object>();

		retVal.put("server_url", ServerConfigurationHelper.getServerURL(getSandbox().getConfiguration()));

		return retVal;
	}

}
