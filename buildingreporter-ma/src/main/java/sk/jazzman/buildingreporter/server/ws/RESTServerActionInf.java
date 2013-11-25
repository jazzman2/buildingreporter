/**
 * 
 */
package sk.jazzman.buildingreporter.server.ws;

import java.util.Map;

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
	 * 
	 * @return
	 */
	public ClientResponse performRequest(Client client, Map<String, Object> actionParams, Map<String, Object> systemParams) throws Exception;

	/**
	 * Handle response
	 * 
	 * @param response
	 * 
	 * @return
	 */
	public Map<String, Object> handleResponse(ClientResponse response) throws Exception;
}
