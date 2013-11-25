/**
 * 
 */
package sk.jazzman.buildingreporter.server.ws;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sk.jazzman.buildingreporter.server.ServerActionInf;
import sk.jazzman.buildingreporter.server.ServerConfigurationHelper;
import sk.jazzman.buildingreporter.server.ws.RegisterMeasureInstrumnet.ParamGetter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author jkovalci
 * 
 */
public class Ping implements ServerActionInf, RESTServerActionInf {

	public static final String getName() {
		return "/ping";
	}

	@Override
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams) throws Exception {
		String servletUrl = ServerConfigurationHelper.getServerURL(systemParams);

		WebResource resource = client.resource(UriBuilder.fromUri(servletUrl).fragment(getName()).build());

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("map", Map.class);
		String object = xstream.toXML(new ParamGetter(actionParams).getConfiguration());

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
