/**
 * 
 */
package sk.jazzman.brmi.jpa.action;

import java.util.Map;

import org.hibernate.Session;

import sk.jazzman.brmi.common.ActionParamGetter;
import sk.jazzman.brmi.domain.measurement.MLog;

/**
 * @author jkovalci
 * 
 */
public class PutMLog extends DefaultJPAActionAbt {
	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		session.beginTransaction();

		MLog mlog = ActionParamGetter.get("mlog", MLog.class, actionParams);

		session.save(mlog);

		session.getTransaction().commit();

		return null;
	}
}
