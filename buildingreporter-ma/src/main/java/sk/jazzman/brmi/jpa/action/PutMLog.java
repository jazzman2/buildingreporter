/**
 * 
 */
package sk.jazzman.brmi.jpa.action;

import java.util.Map;

import org.hibernate.Session;

import sk.jazzman.brmi.common.ActionParamGetter;
import sk.jazzman.brmi.common.ParameterBuilder;
import sk.jazzman.brmi.domain.measurement.MLogArduinoInf;

/**
 * @author jkovalci
 * 
 */
public class PutMLog extends DefaultJPAActionAbt {
	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		session.beginTransaction();

		MLogArduinoInf mlog = ActionParamGetter.get("mlog", MLogArduinoInf.class, actionParams);

		session.save(mlog);

		session.getTransaction().commit();

		return new ParameterBuilder().setParameter("mlog", mlog).build();
	}
}
