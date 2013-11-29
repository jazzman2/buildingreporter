/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.util.Map;

import org.hibernate.Session;

/**
 * @author jano
 * 
 */
public abstract class DefaultJPAActionAbt implements JPAActionInf {

	@Override
	public Map<String, Object> doPreAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {
		return actionParams;
	}

	@Override
	public Map<String, Object> doPostAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {
		return actionParams;
	}
}
