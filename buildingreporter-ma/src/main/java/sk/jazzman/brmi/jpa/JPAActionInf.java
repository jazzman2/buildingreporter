/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.util.Map;

import org.hibernate.Session;

import sk.jazzman.brmi.common.ActionInf;

/**
 * JPA Action
 * 
 * @author jano
 * 
 */
public interface JPAActionInf extends ActionInf {

	/**
	 * Do Pre Action
	 * 
	 * @param actionParams
	 * @param systemParams
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> doPreAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception;

	/**
	 * Do Action
	 * 
	 * @param actionParams
	 * @param systemParams
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception;

	/**
	 * Do Post Action
	 * 
	 * @param actionParams
	 * @param systemParams
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> doPostAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception;

}
