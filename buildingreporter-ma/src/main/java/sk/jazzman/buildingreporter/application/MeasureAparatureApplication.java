/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.util.Map;

/**
 * @author jkovalci
 * 
 */
public class MeasureAparatureApplication implements MeasureAparatureInf {

	public static void main(String[] args) {

	}

	@Override
	public void registerArduinoHandler(String name, ArduinoActionHandlerInf handler, Map<String, Object> configuration) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerServerHandlers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerConfigurationHandler() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object callArduinoAction(ArduinoActionInf action, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object callServerAction(ServerActionInf action, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
