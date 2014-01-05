/**
 * 
 */
package sk.jazzman.brmi.jpa.action;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sk.jazzman.brmi.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;
import sk.jazzman.buildingreporter.domain.utils.ActionParamGetter;

/**
 * @author jano
 * 
 */
public class GetMLogs extends DefaultJPAActionAbt {

	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		session.beginTransaction();

		Long id = (Long) ActionParamGetter.get("mlog.id", actionParams);

		Criteria c = session.createCriteria(MLog.class);

		if (id != null) {
			c.add(Restrictions.eq("id", id));
		}

		Object retVal;

		List<Object> data = c.list();

		session.getTransaction().commit();

		if (id != null && data != null) {
			retVal = data.iterator().next();
		} else {
			retVal = data;
		}

		return new ActionParamBuilder().put("value", retVal).build();
	}
}
