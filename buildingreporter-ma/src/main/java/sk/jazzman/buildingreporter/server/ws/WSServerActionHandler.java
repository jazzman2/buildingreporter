/**
 * 
 */
package sk.jazzman.buildingreporter.server;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.server.ws.RESTServerActionInf;

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
public class WSServerActionHandler implements ServerActionHandlerInf {

	private static final Logger logger = LoggerFactory.getLogger(WSServerActionHandler.class);
	private ServerActionRegisterInf actionRegister;
	private Client client;
	private final Map<String, Object> configuration;

	/**
	 * {@link Constructor}
	 * 
	 * @param configuration
	 * 
	 */
	public WSServerActionHandler(Map<String, Object> configuration) {
		this.configuration = configuration;

		try {
			init(configuration);
		} catch (Exception e) {

		}
	}

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		return logger;
	}

	@Override
	public ServerActionRegisterInf getActionRegister() {
		return actionRegister;
	}

	@Override
	public Map<String, Object> getConfiguration() {
		return configuration;
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
	private void init(Map<String, Object> configuration) throws Exception {
		actionRegister = new DefaultActionRegister();

		ClientConfig config = new DefaultClientConfig();

		client = Client.create(config);
	}

	@Override
	public Map<String, Object> call(String actionName, Map<String, Object> actionParams) throws Exception {
		RESTServerActionInf action = (RESTServerActionInf) getActionRegister().getAction(actionName);

		Map<String, Object> systemParams = createSystemParams();

		ClientResponse response = action.performRequest(getClient(), actionParams, systemParams);

		return action.handleResponse(response);
	}

	/**
	 * Build System Parameters
	 * 
	 * @return
	 */
	protected Map<String, Object> createSystemParams() {
		Map<String, Object> retVal = new HashMap<String, Object>();

		return retVal;
	}

}
