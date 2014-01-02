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
import sk.jazzman.brmi.domain.measurement.MLogArduinoInf;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ServerConfigurationHelper;
import sk.jazzman.brmi.server.ws.action.PutMLog;
import sk.jazzman.buildingreporter.domain.measurement.MLogInf;

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

	public static final int NOT_INITIALIZED = 0;
	public static final int INITIALIZED = 1;
	public static final int CONNECTED = 2;
	public static final int DISCONNECTED = 2;

	private int initState = NOT_INITIALIZED;
	private int connectionState = DISCONNECTED;

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
	 * Return state
	 * 
	 * @return
	 */
	public synchronized int getConnectionState() {
		return connectionState;
	}

	/**
	 * Setter connection state state
	 * 
	 * @param state
	 */
	private synchronized void setConnectionState(int connectionState) {
		this.connectionState = connectionState;
	}

	/**
	 * Return state
	 * 
	 * @return
	 */
	public synchronized int getInitializationState() {
		return initState;
	}

	/**
	 * Setter initialization state
	 * 
	 * @param initState
	 */
	private synchronized void setInitializationState(int initState) {
		this.initState = initState;
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

		setInitializationState(NOT_INITIALIZED);

		actionRegister = new WSActionRegister();
		actionRegister.registerAll();

		ClientConfig config = new DefaultClientConfig();

		client = Client.create(config);

		initCoreEventHandler(sandbox);

		setInitializationState(INITIALIZED);
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
				register(CoreConfigurationHelper.EVENT_MLOG_PUT, new CoreEventResolverInf() {
					@Override
					public void resolve(CoreEventInf event) throws Exception {
						Map<String, Object> params = (Map<String, Object>) event.getParameters();

						Object value = params.get("mlog");

						if (value instanceof MLogArduinoInf) {
							try {
								perform(PutMLog.getName(), new ParameterBuilder().setParameter("mlog", value).build());
							} catch (Exception ex) {
								getLogger().error("Could not to sent to server!", ex);
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

						Object value = params.get("mlog");

						if (value instanceof MLogInf) {
							MLogInf log = (MLogInf) value;

							// FIXME: set id
							// log.setId(id);
							try {
								perform(PutMLog.getName(), new ParameterBuilder().setParameter("mlog", value).build());
							} catch (Exception ex) {
								getLogger().error("Could not to sent to server!", ex);
							}
						} else {
							throw new UnsupportedDataTypeException("Not supported data type!");
						}
					}
				});
			}
		};

		sandbox.getCoreEventManager().register("ServerCoreEventHandler", coreEventHandler);
	}

	@Override
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception {

		RESTServerActionInf action = (RESTServerActionInf) getActionRegister().get(actionName);

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

	/**
	 * ? true if is connected
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return isInitialized() && (CONNECTED == getConnectionState());
	}

	/**
	 * ? true if is initialized
	 * 
	 * @return
	 */
	public boolean isInitialized() {
		return INITIALIZED == getInitializationState();
	}
}
