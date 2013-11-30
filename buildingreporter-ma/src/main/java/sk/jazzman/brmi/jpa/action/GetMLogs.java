/**
 * 
 */
package sk.jazzman.brmi.jpa.action;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sk.jazzman.brmi.domain.measurement.MLog;

/**
 * @author jano
 * 
 */
public class GetMLogs extends DefaultJPAActionAbt {

	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		boolean closeAfterDone;

		session.beginTransaction();

		List<Object> retVal = session.createCriteria(MLog.class).add(Restrictions.idEq(Long.valueOf(0))).list();

		session.getTransaction().commit();

		return null;
	}
}
