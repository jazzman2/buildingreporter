/**
 * 
 */
package sk.jazzman.buildingreporter.server.ws;

import java.lang.reflect.Constructor;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sk.jazzman.buildingreporter.server.ServerConfigurationHelper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author jano
 * 
 */
public class RegisterMeasureInstrumnet implements RESTServerActionInf {

	/**
	 * Return action name
	 * 
	 * @return
	 */
	public static final String getName() {
		return "/register";
	}

	@Override
	public Map<String, Object> handleResponse(ClientResponse response) throws Exception {

		return null;
	}

	@Override
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams) throws Exception {
		String servletUrl = ServerConfigurationHelper.getServerURL(systemParams);

		WebResource resource = client.resource(UriBuilder.fromUri(servletUrl).fragment(getName()).build());

		resource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, new ParamGetter(actionParams).getConfiguration());

		return null;
	}

	public static class ParamBuilder {

	}

	public static class ParamGetter {

		public static final String INPUT_CONFIGURATION = "p_input_configuration";

		private final Map<String, Object> actionParams;

		/**
		 * {@link Constructor}
		 * 
		 * @param actionParams
		 */
		public ParamGetter(Map<String, Object> actionParams) {
			this.actionParams = actionParams;
		}

		public Map<String, Object> getConfiguration() {
			Map<String, Object> retVal = (Map<String, Object>) actionParams.get(INPUT_CONFIGURATION);

			return retVal;
		}
	}
}
