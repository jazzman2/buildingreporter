/**
 * 
 */
package sk.jazzman.brmi.jpa;

import sk.jazzman.brmi.common.DefaultActionRegisterAbt;
import sk.jazzman.brmi.jpa.action.GetMLogs;
import sk.jazzman.brmi.jpa.action.PutMLog;

/**
 * JPA Action Register
 * 
 * @author jano
 * 
 */
public class JPAActionRegister extends DefaultActionRegisterAbt<JPAActionInf> {

	@Override
	public void registerAll() throws Exception {
		register("GET-mlog", new GetMLogs());
		register("PUT-mlog", new PutMLog());
	}

}
