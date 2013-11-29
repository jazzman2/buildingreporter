/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sk.jazzman.buildingreporter.domain.measurement.MLog;

/**
 * @author jano
 * 
 */
public class GetMLogs extends DefaultJPAActionAbt {

	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		boolean closeAfterDone;
		if (session.getTransaction() == null) {
			session.beginTransaction();
			closeAfterDone = true;
		} else {
			closeAfterDone = false;
		}

		List<Object> retVal = session.createCriteria(MLog.class).add(Restrictions.idEq(Long.valueOf(0))).list();

		if (closeAfterDone) {
			session.getTransaction().commit();
		}

		return null;
	}
}
