/**
 * 
 */
package sk.jazzman.buildingreporter.server.soap;

import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 * @author jano
 * 
 */
public class RegisterMeasureInstrumnet implements SOAPServerActionInf {

	/**
	 * Return action name
	 * 
	 * @return
	 */
	public static final String getName() {
		return "register";
	}

	@Override
	public SOAPMessage createRequest(Map<String, Object> params) throws Exception {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage retVal = mf.createMessage();

		SOAPPart part = retVal.getSOAPPart();
		SOAPEnvelope envelope = part.getEnvelope();

		SOAPBody body = envelope.getBody();

		return retVal;
	}

	@Override
	public Map<String, Object> handleResponse(SOAPMessage response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
