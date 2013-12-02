/**
 * 
 */
package sk.jazzman.brmi.server.ws;

import java.lang.reflect.Constructor;

import sk.jazzman.brmi.common.DefaultActionRegisterAbt;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ws.action.RegisterMeasureInstrumnet;

/**
 * Default Server Action Register
 * 
 * @author jano
 * 
 */
public class WSActionRegister extends DefaultActionRegisterAbt<ServerActionInf> {

	/**
	 * {@link Constructor}
	 * 
	 */
	public WSActionRegister() {

	}

	/**
	 * Register Actions
	 */
	@Override
	public void registerActions() throws Exception {
		registerAction(RegisterMeasureInstrumnet.getName(), new RegisterMeasureInstrumnet());
	}
}