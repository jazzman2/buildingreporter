/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.apache.commons.configuration.Configuration;

import sk.jazzman.buildingreporter.common.BPartConverterAbt;
import sk.jazzman.buildingreporter.common.MLogConverter;
import sk.jazzman.buildingreporter.common.UnitTypeConverterAbt;
import sk.jazzman.buildingreporter.domain.manager.SerializationManagerInf;
import sk.jazzman.buildingreporter.domain.measurement.MLog;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author jano
 * 
 */
public class XStreamManager implements SerializationManagerInf {
	private XStream xstream;

	Configuration configuration;

	/**
	 * {@link Constructor}
	 */
	public XStreamManager() {
		init();
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Init manager
	 */
	private void init() {
		xstream = new XStream(new DomDriver());
		xstream.alias("map", Map.class);
		xstream.alias("mlog", MLog.class);
		xstream.processAnnotations(MLog.class);

		xstream.registerConverter(new MLogConverter() {
			@Override
			protected Configuration getConfiguration() {
				return configuration;
			}
		});
		xstream.registerConverter(new UnitTypeConverterAbt() {
			@Override
			public Configuration getConfiguration() {
				return configuration;
			}
		});
		xstream.registerConverter(new BPartConverterAbt() {

			@Override
			public Configuration getConfiguration() {
				return configuration;
			}
		});
		xstream.aliasField("sensorId", MLog.class, "item");
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

	@Override
	public synchronized <T> T toObject(String byteArray) {
		return (T) getXStream().fromXML(byteArray);
	}

	@Override
	public synchronized String toByteArray(Object object) {
		return toXML(object);
	}
}
