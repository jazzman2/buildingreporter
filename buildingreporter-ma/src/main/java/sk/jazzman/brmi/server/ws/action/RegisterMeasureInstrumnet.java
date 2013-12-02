/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.ParameterGetter;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ws.RESTServerActionInf;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Register Client Server Action
 * 
 * @author jano
 * 
 */
public class RegisterMeasureInstrumnet implements RESTServerActionInf, ServerActionInf {

	private static final Logger log = LoggerFactory.getLogger(RegisterMeasureInstrumnet.class);

	/**
	 * Return action name
	 * 
	 * @return
	 */
	public static final String getName() {
		return "/register";
	}

	/**
	 * Return logger
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return log;
	}

	@Override
	public Map<String, Object> handleResponse(ClientResponse response) throws Exception {

		return null;
	}

	@Override
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams, SandboxInf sandbox) throws Exception {
		String servletUrl = (String) systemParams.get("server_url");

		URI uri = UriBuilder.fromUri(servletUrl + getName() + "/register").build();

		WebResource resource = client.resource(uri);

		getLogger().debug("Call request to: " + uri.getPath());

		String object = sandbox.getXStreamManager().toXML(new ParameterGetter().getConfiguration(actionParams));

		return resource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, object);
	}

}