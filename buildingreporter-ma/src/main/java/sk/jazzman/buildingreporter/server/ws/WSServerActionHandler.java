/**
 * 
 */
package sk.jazzman.buildingreporter.server.ws;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.common.DefaultActionHandlerAbt;
import sk.jazzman.buildingreporter.server.ServerActionInf;
import sk.jazzman.buildingreporter.server.ServerConfigurationHelper;

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
	public void init(Map<String, Object> configuration) throws Exception {
		super.init(configuration);

		actionRegister = new WSActionRegister();
		actionRegister.registerActions();

		ClientConfig config = new DefaultClientConfig();

		client = Client.create(config);
	}

	@Override
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception {
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

		retVal.put(ServerConfigurationHelper.SERVER_URL, ServerConfigurationHelper.getServerURL(getConfiguration()));

		return retVal;
	}

}
