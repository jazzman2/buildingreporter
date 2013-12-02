/**
 * 
 */
package sk.jazzman.brmi.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazman.brmi.core.CoreConfigurationHelper;
import sk.jazman.brmi.core.CoreEvent;
import sk.jazzman.brmi.application.SandboxInf;
import sk.jazzman.brmi.common.DefaultActionHandlerAbt;
import sk.jazzman.brmi.common.ParameterBuilder;

/**
 * Arduino Action Handler Abstract
 * 
 * @author jkovalci
 * 
 * @see https://sites.google.com/site/projectrobogobydownloads/
 */
public class ArduinoActionHandler extends DefaultActionHandlerAbt<ArduinoActionInf> {

	private static final Logger logger = LoggerFactory.getLogger(ArduinoActionHandler.class);
	private SerialPort serialPort;

	private BufferedReader input;
	private OutputStream output;

	private SerialPortEventListener listener;

	/**
	 * {@link Constructor}
	 * 
	 * @param configuraion
	 * 
	 */
	public ArduinoActionHandler() {

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
	 * Getter {@link SerialPort}
	 * 
	 * @return
	 */
	public SerialPort getSerialPort() {
		return serialPort;
	}

	/**
	 * Getter serial port event listener
	 * 
	 * @return
	 */
	protected SerialPortEventListener getSerialPortEventListener() {
		return listener;
	}

	@Override
	public void init(SandboxInf sandbox) throws Exception {
		getLogger().debug("Init Arduino Action Handler ... ");

		super.init(sandbox);

		actionRegister = new ArduinoActionRegister();
		actionRegister.registerActions();

		listener = new SerialPortEventListener() {
			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
					try {
						String inputLine = input.readLine();

						getLogger().debug("Read from serial port  value " + inputLine);

						getSandbox().getCoreEventManager().fireEvent(
								new CoreEvent(CoreConfigurationHelper.EVENT_ARDUINO_TEMTERATURE_READ, new ParameterBuilder().setParameter("value", inputLine), ArduinoActionHandler.class));

					} catch (Exception e) {
						getLogger().error("Could not read data!");
					}
				}
			}
		};

		initSerialPort();

		getLogger().debug("Init Arduino Action Handler ... Done ");

	}

	/**
	 * Init serial port
	 * 
	 * @param configuration
	 */
	private void initSerialPort() {
		getLogger().debug("Init serial port");

		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		Configuration configuration = getSandbox().getConfiguration();

		String portName = ArduinoConfigurationHepler.getPort(configuration);
		Integer bitrate = ArduinoConfigurationHepler.getBitrate(configuration);
		Integer timeout = ArduinoConfigurationHepler.getPortTimeOut(configuration);

		if (portName == null) {
			throw new IllegalStateException("Null object!");
		}

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

			if (currPortId.getName().equals(portName)) {
				portId = currPortId;
				break;
			}

		}

		if (portId == null) {
			// getLogger().error("Could not find serial port.");
			// return;
			throw new IllegalStateException("Serial port does not exist!");

		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), timeout);

			// set port parameters
			serialPort.setSerialPortParams(bitrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(getSerialPortEventListener());
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			getLogger().error("Could not init serial port", e);
		}

		getLogger().debug("Init serial port ... DONE");
	}

	@Override
	public Map<String, Object> perform(String actionName, Map<String, Object> actionParams) throws Exception {
		if (actionName == null) {
			throw new IllegalArgumentException("Null argument");
		}

		ArduinoActionInf action = getActionRegister().getAction(actionName);

		Map<String, Object> retVal;

		if (action != null) {
			Map<String, Object> arduinoActionParam = buildArduinoActionParam(actionParams);
			retVal = action.performAction(arduinoActionParam);
		} else {
			throw new IllegalStateException("Null object!");
		}

		return retVal;
	}

	/**
	 * Build Arduino Action Param
	 * 
	 * @param actionParam
	 * @return
	 */
	public Map<String, Object> buildArduinoActionParam(Map<String, Object> actionParam) {

		// TODO: pridaj configuration
		return actionParam;
	}

	/**
	 * Destroy Handler
	 * 
	 * @throws Exception
	 */
	public synchronized void destroy() throws Exception {
		serialPort.removeEventListener();
		serialPort.close();
	}
}
