package sk.jazzman.buildingreporter.application;

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
	public void registerServerHandlers() throws Exception;

	/**
	 * Register Configuration Handler
	 */
	public void registerConfigurationHandler() throws Exception;

	/**
	 * ? true if is initialized
	 * 
	 * @return
	 */
	public boolean isInitialized();

}
