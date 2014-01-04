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
import sk.jazzman.brmi.server.ws.action.RegisterMeasureInstrumnet;
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

	private final ServerActionHandlerStateInf state = new ServerActionHandlerStateInf() {
		private int initState = NOT_INITIALIZED;
		private int connectionState = DISCONNECTED;

		@Override
		public synchronized void setInitializationState(int initState) {
			this.initState = initState;
		}

		@Override
		public synchronized void setConnectionState(int connectionState) {
			this.connectionState = connectionState;
		}

		@Override
		public synchronized int getInitializationState() {
			return initState;
		}

		@Override
		public synchronized int getConnectionState() {
			return connectionState;
		}
	};

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
	public synchronized ServerActionHandlerStateInf getState() {
		return state;
	}

	/**
	 * init action handler
	 * 
	 * @param configuration
	 * 
	 */
	@Override
	public synchronized void init(SandboxInf sandbox) throws Exception {
		super.init(sandbox);

		if (!isInitialized()) {
			getState().setInitializationState(ServerActionHandlerStateInf.INITIALIZATION_IN_PROGRESS);

			getLogger().info("Init Server Action Handler. [START]");

			actionRegister = new WSActionRegister();
			actionRegister.registerAll();

			ClientConfig config = new DefaultClientConfig();

			client = Client.create(config);

			initCoreEventHandler(sandbox);

			getState().setInitializationState(ServerActionHandlerStateInf.INITIALIZED);

			getLogger().info("Init Server Action Handler. [DONE]");
		}
	}

	/**
	 * Ensure connect to server
	 * 
	 * @throws Exception
	 */
	private synchronized void ensureConnect() throws Exception {

		// setConnectionState(DISCONNECTED);

		ensureInitialize();

		if (isInitialized() && !isConnected()) {
			getLogger().info("Register measure instrument started.");

			Map<String, Object> retVal = performServerAction(RegisterMeasureInstrumnet.getName(), new ParameterBuilder().setParameter("configuration", getSandbox().getConfiguration()).build());
			Long id = RegisterMeasureInstrumnet.Output.getId(retVal);

			if (retVal != null) {
				getState().setConnectionState(ServerActionHandlerStateInf.CONNECTED);

				getLogger().info("Measure Instrument SUCCESSFUL CONNECTED with id=" + id);
			} else {
				getLogger().error("Measure Instrument COULD NOT TO CONNECT");
			}

		}
	}

	/**
	 * Ensure init
	 * 
	 * @throws Exception
	 */
	private synchronized void ensureInitialize() throws Exception {

		if (!isInitialized()) {
			init(getSandbox());
		}
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

		String cehName = "ServerCoreEventHandler";

		CoreEventHandlerInf ceh = sandbox.getCoreEventManager().get(cehName);

		if (ceh == null) {
			sandbox.getCoreEventManager().register("ServerCoreEventHandler", coreEventHandler);
		} else if (!ceh.equals(coreEventHandler)) {
			sandbox.getCoreEventManager().unregister("ServerCoreEventHandler");
			sandbox.getCoreEventManager().register("ServerCoreEventHandler", coreEventHandler);
		}

	}

	@Override
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception {
		Map<String, Object> retVal;

		// if (isConnected()) {
		//
		// } else {
		// retVal = null;
		// }
		//
		// if (!isConnected()){
		//
		// }

		ensureConnect();

		if (isConnected()) {
			retVal = performServerAction(actionName, actionParams);
		} else {
			retVal = null;
		}

		return retVal;
	}

	/**
	 * Perform Server Action
	 * 
	 * @param actionName
	 * @param actionParams
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> performServerAction(String actionName, Map<String, Object> actionParams) throws Exception {
		RESTServerActionInf action = (RESTServerActionInf) getActionRegister().get(actionName);

		Map<String, Object> systemParams = createSystemParams();

		ClientResponse response = action.performRequest(getClient(), actionParams, systemParams, getSandbox());

		return action.handleResponse(response, getSandbox());
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
		return isInitialized() && (ServerActionHandlerStateInf.CONNECTED == getState().getConnectionState());
	}

	/**
	 * ? true if is initialized
	 * 
	 * @return
	 */
	public boolean isInitialized() {
		return ServerActionHandlerStateInf.INITIALIZED == getState().getInitializationState();
	}
}
