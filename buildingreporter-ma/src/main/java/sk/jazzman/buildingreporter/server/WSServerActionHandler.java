/**
 * 
 */
package sk.jazzman.buildingreporter.server;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.buildingreporter.server.soap.SOAPServerActionInf;

/**
 * Web Service Server Action Handler
 * 
 * @author jano
 * 
 */
public class WSServerActionHandler implements ServerActionHandlerInf, SOAPHandler<SOAPMessageContext> {

	private static final Logger logger = LoggerFactory.getLogger(WSServerActionHandler.class);
	private ServerActionRegisterInf actionRegister;
	private SOAPConnection connection;
	private final Map<String, Object> configuration;

	/**
	 * {@link Constructor}
	 * 
	 * @param configuration
	 * 
	 */
	public WSServerActionHandler(Map<String, Object> configuration) {
		this.configuration = configuration;

		try {
			init(configuration);
		} catch (Exception e) {

		}
	}

	/**
	 * Getter logger
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		return logger;
	}

	@Override
	public ServerActionRegisterInf getActionRegister() {
		return actionRegister;
	}

	@Override
	public Map<String, Object> getConfiguration() {
		return configuration;
	}

	/**
	 * Return SOAP Connection
	 * 
	 * @return
	 */
	private SOAPConnection getConnection() {
		return connection;
	}

	/**
	 * init action handler
	 * 
	 * @param configuration
	 * 
	 */
	private void init(Map<String, Object> configuration) throws Exception {
		actionRegister = new DefaultActionRegister();

		SOAPConnectionFactory soapConnectonFactory = SOAPConnectionFactory.newInstance();
		connection = soapConnectonFactory.createConnection();
	}

	@Override
	public Map<String, Object> call(String actionName, Map<String, Object> actionParams) throws Exception {
		SOAPServerActionInf action = (SOAPServerActionInf) getActionRegister().getAction(actionName);

		SOAPMessage response = getConnection().call(action.createRequest(actionParams), ServerConfigurationHelper.getServerURL(getConfiguration()));

		return action.handleResponse(response);
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
}
