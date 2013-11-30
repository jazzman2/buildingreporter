/**
 * 
 */
package sk.jazzman.brmi.common;

import java.lang.reflect.Constructor;

import sk.jazzman.brmi.application.SandboxInf;

/**
 * Default Action Handler
 * 
 * @author jano
 * 
 */
public abstract class DefaultActionHandlerAbt<A extends ActionInf> implements ActionHandlerInf<A> {

	protected ActionRegisterInf<A> actionRegister;
	private SandboxInf sandbox;

	/**
	 * {@link Constructor}
	 * 
	 */
	public DefaultActionHandlerAbt() {

	}

	@Override
	public ActionRegisterInf<A> getActionRegister() {
		return actionRegister;
	}

	@Override
	public SandboxInf getSandbox() {
		return sandbox;
	}

	/**
	 * init action handler
	 * 
	 * @param sandbox
	 * 
	 */
	@Override
	public void init(SandboxInf sandbox) throws Exception {
		if (sandbox == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		this.sandbox = sandbox;
	}

}
