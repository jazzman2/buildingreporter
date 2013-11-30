/**
 * 
 */
package sk.jazzman.brmi.jpa;

import sk.jazzman.brmi.common.DefaultActionRegisterAbt;
import sk.jazzman.brmi.jpa.action.GetMLogs;

/**
 * JPA Action Register
 * 
 * @author jano
 * 
 */
public class JPAActionRegister extends DefaultActionRegisterAbt<JPAActionInf> {

	@Override
	public void registerActions() throws Exception {
		registerAction("GET-mlog", new GetMLogs());
	}

}
