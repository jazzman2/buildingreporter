/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.arduino.ArduinoManagerInf;
import sk.jazzman.buildingreporter.arduino.DefaultArduinoActionManager;

/**
 * @author jkovalci
 * 
 */
public class MeasureInstrumentApplication implements MeasureInstrumentInf {

	private ArduinoManagerInf arduinoManager;

	private static final Logger logger = LoggerFactory.getLogger(MeasureInstrumentApplication.class);

	/**
	 * {@link Constructor}
	 */
	public MeasureInstrumentApplication() {
		init();
	}

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	private void init() {
		arduinoManager = new DefaultArduinoActionManager();
		try {
			((DefaultArduinoActionManager) arduinoManager).init();
		} catch (Exception e) {
			getLogger().error("Could not to init arduino action manager", e);
		}
	}

	@Override
	public void registerServerHandlers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerConfigurationHandler() {
		// TODO Auto-generated method stub

	}

}
