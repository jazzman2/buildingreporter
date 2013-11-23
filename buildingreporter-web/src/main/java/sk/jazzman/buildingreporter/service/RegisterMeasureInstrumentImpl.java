/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register Measure Instrumnet Implementation
 * 
 * @author jano
 * 
 */
@WebService(endpointInterface = "sk.jazzman.buildingreporter.service.RegisterMeasureInstrument")
public class RegisterMeasureInstrumentImpl implements RegisterMeasureInstrumet {
	private static Logger logger = LoggerFactory.getLogger(RegisterMeasureInstrumentImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sk.jazzman.buildingreporter.service.RegisterMeasureInstrumet#register
	 * (java.lang.String)
	 */
	@Override
	public String register(String measureInstrumetKey) {
		if (measureInstrumetKey == null) {
			throw new IllegalArgumentException("Null argument");
		}

		getLogger().debug("Registering measure instrument: " + measureInstrumetKey);

		return measureInstrumetKey;
	}

	/**
	 * Getter Logger
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}
}
