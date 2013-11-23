/**
 * 
 */
package sk.jazzman.buildingreporter.server.soap;

import java.util.Map;

import javax.xml.soap.SOAPMessage;

/**
 * @author jano
 * 
 */
public interface SOAPServerActionInf {

	/**
	 * Create Request
	 * 
	 * @param params
	 * @return
	 */
	public SOAPMessage createRequest(Map<String, Object> params) throws Exception;

	/**
	 * Handle response
	 * 
	 * @param response
	 * @return
	 */
	public Map<String, Object> handleResponse(SOAPMessage response) throws Exception;
}
