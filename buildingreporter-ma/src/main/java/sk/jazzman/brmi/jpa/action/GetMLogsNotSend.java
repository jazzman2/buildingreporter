/**
 * 
 */
package sk.jazzman.brmi.jpa.action;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import sk.jazzman.brmi.domain.measurement.MLogNotSend;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;

/**
 * @author jano
 * 
 */
public class GetMLogsNotSend extends DefaultJPAActionAbt {

	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		session.beginTransaction();

		List<Object> retVal = session.createCriteria(MLogNotSend.class).list();

		session.getTransaction().commit();

		return new ActionParamBuilder().put("value", retVal).build();
	}
}
