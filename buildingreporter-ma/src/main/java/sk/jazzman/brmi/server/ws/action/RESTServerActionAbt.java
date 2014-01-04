/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ws.RESTServerActionInf;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Abstract Rest Service Action
 * 
 * @author jano
 * 
 */
public abstract class RESTServerActionAbt implements ServerActionInf, RESTServerActionInf {
	private static final Logger log = LoggerFactory.getLogger(RESTServerActionAbt.class);

	@Override
	public Map<String, Object> handleResponse(ClientResponse response, SandboxInf sandbox) throws Exception {
		Map<String, Object> retVal;

		if (response != null && response.getStatus() == ClientResponse.Status.CREATED.getStatusCode()) {
			String object = response.getEntity(String.class);
			log.debug("Incomming object: " + object);

			if (object == null || "".equals(object)) {
				retVal = null;
			} else {
				retVal = (Map<String, Object>) sandbox.getXStreamManager().toObject(object);
			}
		} else {
			retVal = null;
		}

		return retVal;
	}
}
