/**
 * 
 */
package sk.jazzman.brmi.jpa.action;

import java.util.Map;

import org.hibernate.Session;

import sk.jazzman.brmi.domain.measurement.MLogNotSend;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;
import sk.jazzman.buildingreporter.domain.utils.ActionParamGetter;

/**
 * Put {@link MLogNotSend}
 * 
 * @author jkovalci
 * 
 */
public class PutMLogNotSend extends DefaultJPAActionAbt {
	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		session.beginTransaction();

		MLogNotSend mlog = ActionParamGetter.get("value", MLogNotSend.class, actionParams);

		session.save(mlog);

		session.getTransaction().commit();

		return new ActionParamBuilder().put("value", mlog).build();
	}
}
