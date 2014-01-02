/**
 * 
 */
package sk.jazzman.brmi.server.ws.action;

import java.util.Map;

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
	@Override
	public Map<String, Object> handleResponse(ClientResponse response, SandboxInf sandbox) throws Exception {
		Map<String, Object> retVal;

		if (response != null && response.getStatus() == ClientResponse.Status.CREATED.getStatusCode()) {
			retVal = (Map<String, Object>) sandbox.getXStreamManager().toObject(response.getEntity(String.class));
		} else {
			retVal = null;
		}

		return retVal;
	}
}
