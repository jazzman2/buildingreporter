package sk.jazzman.buildingreporter.application;

import java.util.Map;

/**
 * Measure Aparature Interface
 * 
 * @author jkovalci
 * 
 */
public interface MeasureInstrumentInf {

	/**
	 * Register Server Handlers
	 */
	public void registerServerHandlers();

	/**
	 * Register Configuration Handler
	 */
	public void registerConfigurationHandler();

//	/**
//	 * Call Arduino Action
//	 * 
//	 * @param actionName
//	 * @param parameters
//	 * 
//	 * @return
//	 */
//	public Object callArduinoAction(String actionName, Map<String, Object> parameters);
//
//	/**
//	 * Call server action
//	 * 
//	 * @param action
//	 * @param parameters
//	 * 
//	 * @return
//	 */
//	public Object callServerAction(ServerActionInf action, Map<String, Object> parameters);
}
