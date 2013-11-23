/**
 * 
 */
package sk.jazzman.buildingreporter.service;

import javax.jws.WebService;

/**
 * Register Measure Instrument Web Service
 * 
 * @author jano
 * 
 */
@WebService
public interface RegisterMeasureInstrumet {
	public String register(String measureInstrumetKey);
}
