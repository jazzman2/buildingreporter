/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.util.HashMap;
import java.util.Map;

import sk.jazzman.buildingreporter.domain.common.RegisterInf;

/**
 * Measure Instrument Register
 * 
 * @author jano
 * 
 */
public class MeasureInstrumentRegister implements RegisterInf {

	private final Map<String, Object> register = new HashMap<String, Object>();

	@Override
	public synchronized void register(String name, Object object) {

	}

	@Override
	public Object get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerAll() throws Exception {
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public Object unregister(String name) {
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public void unregisterAll() {
		throw new UnsupportedOperationException("Not supported operation!");
	}

}
