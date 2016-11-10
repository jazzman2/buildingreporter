package sk.jazzman.brmi.application;


import org.apache.commons.configuration2.Configuration;
import sk.jazzman.brmi.arduino.ArduinoActionInf;
import sk.jazzman.brmi.common.ActionHandlerInf;
import sk.jazzman.brmi.core.CoreEventManagerInf;
import sk.jazzman.brmi.jpa.JPAActionInf;
import sk.jazzman.brmi.server.ServerActionInf;

/**
 * Sandbox Interface
 *
 * @author jkovalci
 */
public interface SandboxInf {
	
	public ActionHandlerInf<JPAActionInf> getJPAActionHandler();
	
	public ActionHandlerInf<ServerActionInf> getServerActionHandler();
	
	public ActionHandlerInf<ArduinoActionInf> getArduinoActinHandler();
	
	public XStreamManager getXStreamManager();
	
	public CoreEventManagerInf getCoreEventManager();
	
	public void init() throws Exception;
	
	public Configuration getConfiguration();
	
	/**
	 * ? true if is initialized
	 *
	 * @return
	 */
	public boolean isInitialized();
	
}
