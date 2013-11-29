/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.common.ActionHandlerInf;
import sk.jazzman.buildingreporter.arduino.ArduinoManagerInf;
import sk.jazzman.buildingreporter.arduino.DefaultArduinoActionManager;
import sk.jazzman.buildingreporter.server.ServerConfigurationHelper;
import sk.jazzman.buildingreporter.server.ws.WSServerActionHandler;

/**
 * @author jkovalci
 * 
 */
public class MeasureInstrumentApplication implements MeasureInstrumentInf {

	private ArduinoManagerInf arduinoManager;
	private ActionHandlerInf serverActionHandler;
	private Map<String, Object> configuration;

	private static final Logger logger = LoggerFactory.getLogger(MeasureInstrumentApplication.class);

	/**
	 * {@link Constructor}
	 */
	public MeasureInstrumentApplication() {

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
	 * Init Application
	 */
	public void init() throws Exception {
		registerConfigurationHandler();
		registerServerHandlers();
		registerArduinoHandlers();
	}

	@Override
	public void registerServerHandlers() throws Exception {
		Map<String, Object> config = new HashMap<String, Object>();
		config.putAll(getConfiguration());

		serverActionHandler = new WSServerActionHandler();
		serverActionHandler.init(config);
	}

	@Override
	public void registerConfigurationHandler() throws Exception {
		configuration = new HashMap<String, Object>();
		configuration.put(ServerConfigurationHelper.SERVER_URL, "http://localhost:8080/buildingreporter-web/mi");
		configuration.put(ApplicationConfigurationHelper.MEASURE_INSTRUMENT_NAME, "mi_test");
		configuration.put(ApplicationConfigurationHelper.MEASURE_INSTRUMENT_MAC_ADDRESS, getMacAddress());
		configuration.put(ApplicationConfigurationHelper.MEASURE_INSTRUMENT_IP_ADDRESS, getIpAddress());
	}

	public void registerArduinoHandlers() {
		arduinoManager = new DefaultArduinoActionManager();
		try {
			((DefaultArduinoActionManager) arduinoManager).init();
		} catch (Exception e) {
			getLogger().error("Could not to init arduino action manager", e);
		}
	}

	/**
	 * Return configuration
	 * 
	 * @return
	 */
	public Map<String, Object> getConfiguration() {
		return configuration;
	}

	/**
	 * Getter Server Action Handler
	 * 
	 * @return
	 */
	public ActionHandlerInf getServerActionHandler() {
		return serverActionHandler;
	}

	/**
	 * Return mac address
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getMacAddress() throws Exception {
		InetAddress ip = InetAddress.getLocalHost();

		Enumeration<NetworkInterface> ifcs = NetworkInterface.getNetworkInterfaces();

		byte[] mac = null;

		while (ifcs.hasMoreElements()) {
			mac = ifcs.nextElement().getHardwareAddress();
			break;
		}

		return new String(mac != null ? mac : new byte[] {});
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
