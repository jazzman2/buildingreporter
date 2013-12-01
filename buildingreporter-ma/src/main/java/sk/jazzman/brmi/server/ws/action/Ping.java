/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ws.RESTServerActionInf;
import sk.jazzman.brmi.server.ws.action.RegisterMeasureInstrumnet.ParamGetter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author jkovalci
 * 
 */
public class Ping implements ServerActionInf, RESTServerActionInf {

	public static final String getName() {
		return "/ping";
	}

	@Override
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams, SandboxInf sandbox) throws Exception {
		String servletUrl = (String) systemParams.get("server_url");

		WebResource resource = client.resource(UriBuilder.fromUri(servletUrl).fragment(getName()).build());

		String object = sandbox.getXStreamManager().toXML(new ParamGetter(actionParams).getConfiguration());

		return resource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, object);
	}

	@Override
	public Map<String, Object> handleResponse(ClientResponse response) throws Exception {
		if (response != null) {

		} else {

		}

		return null;
	}

}
