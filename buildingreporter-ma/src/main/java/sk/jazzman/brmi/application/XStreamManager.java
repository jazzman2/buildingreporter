/**
 * 
 */
package sk.jazzman.brmi.application;

import java.lang.reflect.Constructor;
import java.util.Map;

import sk.jazzman.brmi.domain.measurement.MLog;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author jano
 * 
 */
public class XStreamManager {
	private final XStream xstream;

	/**
	 * {@link Constructor}
	 */
	public XStreamManager() {
		xstream = new XStream(new DomDriver());
		xstream.alias("map", Map.class);
		xstream.processAnnotations(MLog.class);
	}

	/**
	 * Getter {@link XStream}
	 * 
	 * @return
	 */
	private synchronized XStream getXStream() {
		return xstream;
	}

	/**
	 * Object to byte array
	 * 
	 * @param object
	 * @return
	 */
	public synchronized String toXML(Object object) {
		return getXStream().toXML(object);
	}

	/**
	 * Byte array to object
	 * 
	 * @param byteArray
	 * @return
	 */
	public synchronized Object toObject(String byteArray) {
		return getXStream().fromXML(byteArray);
	}
}
