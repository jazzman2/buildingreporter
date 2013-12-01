/**
 * 
 */
package sk.jazzman.brmi.arduino;

import java.lang.reflect.Constructor;

import sk.jazzman.brmi.application.SandboxInf;

/**
 * @author jano
 * 
 */
public class ArduinoThread extends Thread {

	private final SandboxInf sandbox;

	/**
	 * {@link Constructor}
	 * 
	 * @param sandbox
	 */
	public ArduinoThread(SandboxInf sandbox) {
		if (sandbox == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		this.sandbox = sandbox;
	}

	/**
	 * Getter {@link SandboxInf}
	 * 
	 * @return
	 */
	public SandboxInf getSandbox() {
		return sandbox;
	}

	@Override
	public void run() {

	};
}
