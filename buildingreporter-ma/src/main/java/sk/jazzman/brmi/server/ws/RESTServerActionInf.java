/**
 * 
 */
package sk.jazzman.brmi.server.ws;

import java.util.Map;

import sk.jazzman.brmi.application.SandboxInf;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author jano
 * 
 */
public interface RESTServerActionInf {

	/**
	 * Perform request
	 * 
	 * @param client
	 * @param actionParams
	 * @param systemParams
	 * @param sandbox
	 * 
	 * @return
	 */
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams, SandboxInf sandbox) throws Exception;

	/**
	 * Handle response
	 * 
	 * @param response
	 * @param sandbox
	 * 
	 * @return
	 */
	public Map<String, Object> handleResponse(ClientResponse response, SandboxInf sandbox) throws Exception;
}
