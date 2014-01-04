/**
 * 
 */
package sk.jazzman.brmi.application;

import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazman.brmi.core.CoreEventManager;
import sk.jazman.brmi.core.CoreEventManagerInf;
import sk.jazzman.brmi.arduino.ArduinoActionHandler;
import sk.jazzman.brmi.arduino.ArduinoActionInf;
import sk.jazzman.brmi.common.ActionHandlerInf;
import sk.jazzman.brmi.jpa.JPAActionHandler;
import sk.jazzman.brmi.jpa.JPAActionInf;
import sk.jazzman.brmi.server.ServerActionInf;
import sk.jazzman.brmi.server.ServerConfigurationHelper;
import sk.jazzman.brmi.server.ws.WSServerActionHandler;

/**
 * @author jkovalci
 * 
 */
public class Sandbox implements SandboxInf {

	private ActionHandlerInf<ArduinoActionInf> arduinoActionHandler;
	private ActionHandlerInf<ServerActionInf> serverActionHandler;
	private ActionHandlerInf<JPAActionInf> jpaActionHandler;
	private XMLConfiguration configuration;
	private XStreamManager xstreamManager;
	private CoreEventManagerInf coreEventManager;

	private static final Logger logger = LoggerFactory.getLogger(Sandbox.class);

	private boolean isInitialized = false;

	/**
	 * {@link Constructor}
	 */
	public Sandbox() {

	}

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	@Override
	public synchronized boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * Init Application
	 */
	@Override
	public void init() throws Exception {

		coreEventManager = new CoreEventManager();
		registerConfigurationHandler();
		registerServerHandlers();
		registerArduinoHandlers();
		registerJPAActionHandlers();
		xstreamManager = new XStreamManager();

		isInitialized = true;
	}

	/**
	 * Register and init JPA
	 * 
	 * @throws Exception
	 */
	private void registerJPAActionHandlers() throws Exception {
		jpaActionHandler = new JPAActionHandler();
		jpaActionHandler.init(this);
	}

	/**
	 * Register and init server
	 * 
	 * @throws Exception
	 */
	private void registerServerHandlers() throws Exception {
		serverActionHandler = new WSServerActionHandler();
		serverActionHandler.init(this);

	}

	/**
	 * Register and init configuration
	 * 
	 * @throws Exception
	 */
	private void registerConfigurationHandler() throws Exception {

		configuration = new XMLConfiguration("brmiconfiguration.xml");
		configuration.setExpressionEngine(new XPathExpressionEngine());

		ApplicationConfigurationHelper.setMacAddress(getMacAddress(), configuration);
		ApplicationConfigurationHelper.setIpAddress(getIpAddress(), configuration);

		// configuration = new
		// PropertiesConfiguration("brmiconfiguration.properties");
		// configuration.setProperty(ApplicationConfigurationHelper.MI_MAC_ADDRESS,
		// );
		// configuration.setProperty(ApplicationConfigurationHelper.MI_IP_ADDRESS,
		// getIpAddress());

		String url = ServerConfigurationHelper.getServerURL(configuration);

		if (null == url) {
			throw new IllegalStateException("Configuration does not exist!");
		} else {
			getLogger().debug("Server IP: " + url);
		}

		getLogger().debug("Configuration: " + ApplicationConfigurationHelper.getSubconfiguration(configuration, null));
	}

	/**
	 * register and init arduino
	 */
	private void registerArduinoHandlers() throws Exception {
		arduinoActionHandler = new ArduinoActionHandler();

		try {
			arduinoActionHandler.init(this);
		} catch (Exception e) {
			getLogger().error("Could not to init arduino handler!");
		}
	}

	/**
	 * Return configuration
	 * 
	 * @return
	 */
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Getter Server Action Handler
	 * 
	 * @return
	 */
	@Override
	public ActionHandlerInf<ServerActionInf> getServerActionHandler() {
		return serverActionHandler;
	}

	@Override
	public ActionHandlerInf<ArduinoActionInf> getArduinoActinHandler() {
		return null;
	}

	@Override
	public ActionHandlerInf<JPAActionInf> getJPAActionHandler() {
		return jpaActionHandler;
	}

	/**
	 * getter {@link XStreamManager}
	 * 
	 * @return
	 */
	@Override
	public XStreamManager getXStreamManager() {
		return xstreamManager;
	}

	@Override
	public CoreEventManagerInf getCoreEventManager() {
		return coreEventManager;
	}

	/**
	 * Return mac address
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getMacAddress() throws Exception {
		// InetAddress ip = InetAddress.getLocalHost();

		Enumeration<NetworkInterface> ifcs = NetworkInterface.getNetworkInterfaces();

		byte[] mac = null;

		while (ifcs.hasMoreElements()) {
			mac = ifcs.nextElement().getHardwareAddress();
			break;
		}

		String retVal;

		if (mac != null) {
			StringBuilder sb = new StringBuilder();

			for (byte b : mac) {
				sb.append(String.format("%02X ", b));
			}

			retVal = sb.toString();

		} else {
			retVal = null;
		}

		return retVal;
	}

	/**
	 * Return ip address
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getIpAddress() throws Exception {
		InetAddress ip = InetAddress.getLocalHost();

		return ip.getHostAddress();
	}
}
