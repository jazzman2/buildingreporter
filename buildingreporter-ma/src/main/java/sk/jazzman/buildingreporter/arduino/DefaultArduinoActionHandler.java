/**
 * 
 */
package sk.jazzman.buildingreporter.arduino;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jano
 * 
 */
public class DefaultArduinoActionHandler extends ArduinoActionHandlerAbt implements SerialPortEventListener {

	private static final Logger logger = LoggerFactory.getLogger(DefaultArduinoActionHandler.class);

	private SerialPort serialPort;

	private BufferedReader input;

	private OutputStream output;

	/**
	 * {@link Constructor}
	 * 
	 */
	public DefaultArduinoActionHandler() {
		super();
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
	 * Getter logger
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	@Override
	public void init(Map<String, Object> configuraion) {
		super.init(configuraion);

		initSerialPort(configuraion);
	}

	/**
	 * Init serial port
	 * 
	 * @param configuration
	 */
	protected void initSerialPort(Map<String, Object> configuration) {
		getLogger().debug("Init serial port");

		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		String portName = (String) configuration.get(ArduinoConfigurationHepler.PARAM_ARDUINO_SERIAL_PORT_NAME);
		Integer bitrate = (Integer) configuration.get(ArduinoConfigurationHepler.PARAM_ARDUINO_BITRATE);
		Integer timeout = (Integer) configuration.get(ArduinoConfigurationHepler.PARAM_ARDUINO_TIME_OUT);

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
			getLogger().error("Could not find serial port.");
			return;
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
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			getLogger().error("Could not init serial port", e);
		}

		getLogger().debug("Init serial port ... DONE");
	}

	@Override
	public void registerActions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();

				getLogger().debug("Read from " + getConfiguration().get(ArduinoConfigurationHepler.PARAM_ARDUINO_SERIAL_PORT_NAME) + " value " + inputLine);

			} catch (Exception e) {
				getLogger().error("Could not read data!");
			}
		}
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
