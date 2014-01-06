/**
 * 
 */
package sk.jazzman.brmi.arduino;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.core.CoreConfigurationHelper;
import sk.jazzman.brmi.core.CoreEvent;
import sk.jazzman.brmi.core.CoreEventHandlerAbt;
import sk.jazzman.brmi.core.CoreEventHandlerInf;
import sk.jazzman.brmi.core.CoreEventInf;
import sk.jazzman.brmi.core.CoreEventResolverInf;
import sk.jazzman.brmi.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.utils.ActionParamBuilder;
import sk.jazzman.buildingreporter.domain.utils.ActionParamGetter;

/**
 * @author jano
 * 
 */
public class ArduinoThread extends Thread {

	private final SandboxInf sandbox;
	private static final Logger logger = LoggerFactory.getLogger(ArduinoThread.class);

	private final CoreEventHandlerInf eventHandler = new ATCoreEventHandler();

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

		getSandbox().getCoreEventManager().register("ArduinoThreadCoreHandler", eventHandler);
	}

	/**
	 * Getter core event handler
	 * 
	 * @return
	 */
	private CoreEventHandlerInf getCoreEventHandler() {
		return eventHandler;
	}

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	/**
	 * Getter {@link SandboxInf}
	 * 
	 * @return
	 */
	public SandboxInf getSandbox() {
		return sandbox;
	}

	// @Override
	// public void run() {
	// super.run();
	//
	// while (true) {
	// if (getSandbox().isInitialized()) {
	// try {
	// createMLog();
	// } catch (Exception e) {
	// getLogger().error("Error fire event!", e);
	// }
	// }
	//
	// try {
	// sleep(10000l);
	// } catch (InterruptedException e) {
	// getLogger().debug(" sleep error", e);
	// // return;
	// }
	// }
	// }
	//
	// private void createMLog() throws Exception {
	//
	// MLogArduinoInf log = new MLog();
	// log.setLogDate(new java.sql.Timestamp(System.currentTimeMillis()));
	// log.setValueMeasured((new Random().nextDouble() * 20.0d) - 10.0d);
	// log.setUnitMeasured("celsius");
	// log.setSensorId("-1");
	//
	// getSandbox().getCoreEventManager().fireEvent(
	// new CoreEvent(CoreConfigurationHelper.EVENT_ARDUINO_TEMTERATURE_READ, new
	// ParameterBuilder().setParameter("value", log).build(),
	// ArduinoThread.this));
	// }

	/**
	 * Core Event Handler
	 * 
	 * @author jano
	 * 
	 */
	private class ATCoreEventHandler extends CoreEventHandlerAbt {

		@Override
		public void registerResolvers() {
			register(CoreConfigurationHelper.EVENT_ARDUINO_TEMTERATURE_READ, new CoreEventResolverInf() {
				@Override
				public void resolve(CoreEventInf event) throws Exception {
					Map<String, Object> params = (Map<String, Object>) event.getParameters();
					String serialData = ActionParamGetter.get("value", String.class, params);

					SandboxInf s = getSandbox();

					if (s.isInitialized()) {
						MLog l;
						try {
							l = (MLog) s.getXStreamManager().toObject(serialData);
						} catch (Exception e) {
							l = null;
							getLogger().info("Could not to read object!", e);
						}

						if (l != null) {
							l.setLogDate(new java.sql.Timestamp(System.currentTimeMillis()));
							getLogger().info("Read log=" + l.toString());

							s.getCoreEventManager().fireEvent(new CoreEvent(//
									CoreConfigurationHelper.EVENT_MLOG_READ, //
									new ActionParamBuilder().put("value", l).build(), //
									ArduinoThread.this));
						}

					} else {
						getLogger().info("Sandbox not initialized yet!");
					}

				}
			});
		}
	}
}
