/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.ParameterGetter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author jkovalci
 * 
 */
public class Ping extends RESTServerActionAbt {

	/**
	 * Return Action Name
	 * 
	 * @return
	 */
	public static final String getName() {
		return "/ping";
	}

	@Override
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams, SandboxInf sandbox) throws Exception {
		String servletUrl = (String) systemParams.get("server_url");

		WebResource resource = client.resource(UriBuilder.fromUri(servletUrl).fragment(getName()).build());

		String object = sandbox.getXStreamManager().toXML(new ParameterGetter().getConfiguration(actionParams));

		return resource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, object);
	}

}
