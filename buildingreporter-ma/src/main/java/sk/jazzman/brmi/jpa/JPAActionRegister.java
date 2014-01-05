/**
 * 
 */
package sk.jazzman.brmi.jpa;

import sk.jazzman.brmi.common.DefaultActionRegisterAbt;
import sk.jazzman.brmi.jpa.action.DeleteMLogNotSend;
import sk.jazzman.brmi.jpa.action.GetMLogs;
import sk.jazzman.brmi.jpa.action.GetMLogsNotSend;
import sk.jazzman.brmi.jpa.action.PutMLog;
import sk.jazzman.brmi.jpa.action.PutMLogNotSend;

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

		register("PUT-mlog_not_send", new PutMLogNotSend());
		register("GET-mlog_not_send", new GetMLogsNotSend());
		register("DELETE-mlog_not_send", new DeleteMLogNotSend());
	}

}
