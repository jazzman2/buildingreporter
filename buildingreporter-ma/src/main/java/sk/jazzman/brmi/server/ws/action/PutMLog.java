/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.ActionParamGetter;
import sk.jazzman.brmi.domain.measurement.MLog;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ServerConfigurationHelper;
import sk.jazzman.brmi.server.ws.RESTServerActionInf;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author jkovalci
 * 
 */
public class PutMLog implements RESTServerActionInf, ServerActionInf {

	/**
	 * Return action name
	 * 
	 * @return
	 */
	public static final String getName() {
		return "/mlog";
	}

	@Override
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams, SandboxInf sandbox) throws Exception {
		String servletUrl = ServerConfigurationHelper.getServerURL(sandbox.getConfiguration());

		URI uri = UriBuilder.fromUri(servletUrl + getName() + "/put").build();

		WebResource resource = client.resource(uri);

		MLog mlog = ActionParamGetter.get("mlog", MLog.class, actionParams);

		String object = sandbox.getXStreamManager().toXML(mlog);

		return resource.type(MediaType.APPLICATION_XML).put(ClientResponse.class, object);
	}

	@Override
	public Map<String, Object> handleResponse(ClientResponse response) throws Exception {

		return null;
	}

}
