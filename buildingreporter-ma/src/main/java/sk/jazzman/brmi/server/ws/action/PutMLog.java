/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sk.jazzman.brmi.application.ApplicationConfigurationHelper;
import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.ActionParamGetter;
import sk.jazzman.brmi.common.ParameterBuilder;
import sk.jazzman.brmi.domain.measurement.MLogArduinoInf;
import sk.jazzman.brmi.server.ServerConfigurationHelper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author jkovalci
 * 
 */
public class PutMLog extends RESTServerActionAbt {

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

		MLogArduinoInf mlog = ActionParamGetter.get("mlog", MLogArduinoInf.class, actionParams);

		ParameterBuilder pb = new ParameterBuilder();
		pb.setParameter("mlog", mlog);
		pb.setParameter("mi.name", ApplicationConfigurationHelper.getName(sandbox.getConfiguration()));

		Object object = sandbox.getXStreamManager().toXML(pb.build());

		return resource.type(MediaType.APPLICATION_XML).put(ClientResponse.class, object);

	}
}
