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
 * Remove {@link MLogNotSend}
 * 
 * @author jkovalci
 * 
 */
public class DeleteMLogNotSend extends DefaultJPAActionAbt {
	@Override
	public Map<String, Object> doAction(Map<String, Object> actionParams, Map<String, Object> systemParams, Session session) throws Exception {

		session.beginTransaction();

		MLogNotSend mlogNotSend = ActionParamGetter.get("value", MLogNotSend.class, actionParams);

		Long retVal = mlogNotSend.getId();

		session.delete(mlogNotSend);

		session.getTransaction().commit();

		return new ActionParamBuilder().put("value", retVal).build();
	}
}
