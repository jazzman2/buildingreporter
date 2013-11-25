/**
 * 
 */
package sk.jazzman.buildingreporter.server.ws;

import java.lang.reflect.Constructor;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.server.ServerActionInf;
import sk.jazzman.buildingreporter.server.ServerConfigurationHelper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams) throws Exception {
		String servletUrl = ServerConfigurationHelper.getServerURL(systemParams);

		URI uri = UriBuilder.fromUri(servletUrl + getName() + "/register").build();

		WebResource resource = client.resource(uri);

		getLogger().debug("Call request to: " + uri.getPath());

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("map", Map.class);
		String object = xstream.toXML(new ParamGetter(actionParams).getConfiguration());

		return resource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, object);
	}

	public static class ParamBuilder {
		private final Map<String, Object> actionParams = new HashMap<String, Object>();

		/**
		 * Set Configuration
		 * 
		 * @param configuration
		 * @return
		 */
		public ParamBuilder setConfiguration(Map<String, Object> configuration) {
			if (configuration == null) {
				throw new IllegalArgumentException("Null argument!");
			}

			actionParams.put(ParamGetter.INPUT_CONFIGURATION, configuration);

			return this;
		}

		/**
		 * Build params
		 * 
		 * @return
		 */
		public Map<String, Object> build() {
			return actionParams;
		}
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
