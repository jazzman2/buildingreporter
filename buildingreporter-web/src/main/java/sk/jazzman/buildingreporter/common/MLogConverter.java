/**
 *
 */
package sk.jazzman.buildingreporter.common;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterMatcher;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.converters.extended.SqlTimeConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.jazzman.buildingreporter.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.measurement.MLogInf;

import java.util.Map;

/**
 * @author jano
 */
public abstract class MLogConverter extends EntityConverterAbt {
	
	private static final Logger log = LoggerFactory.getLogger(MLogConverter.class);
	
	/**
	 * Getter Logger
	 *
	 * @return
	 */
	private static Logger getLog() {
		return log;
	}
	
	protected abstract Configuration getConfiguration();
	
	@Override
	protected void registerAttributeHandlers() {
		register("id", new AttributeHandler("id", new LongConverter()));
		register("logDate", new AttributeHandler("logDate", new SqlTimeConverter()));
		register("valueMeasured", new AttributeHandler("valueMeasured", new LongConverter()));
		register("valueTransformed", new AttributeHandler("valueTransformed", new LongConverter()));
		register("unitMeasured", new AttributeHandler("unitMeasured", new UnitTypeConverterAbt() {
			@Override
			public Configuration getConfiguration() {
				return MLogConverter.this.getConfiguration();
			}
		}));
		register("unitTransformed", new AttributeHandler("unitTransformed", new UnitTypeConverterAbt() {
			@Override
			public Configuration getConfiguration() {
				return MLogConverter.this.getConfiguration();
			}
		}));
		register("instrumentId", new AttributeHandler("instrument", new InstrumentNameConverter()));
		register("sensorId", new AttributeHandler("item", new BPartConverterAbt() {
			@Override
			public Configuration getConfiguration() {
				return MLogConverter.this.getConfiguration();
			}
		}));
	}
	
	@Override
	public boolean canConvert(Class clazz) {
		return clazz != null && clazz.equals(MLogInf.class);
	}
	
	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
		throw new UnsupportedOperationException("Not supported yet!");
	}
	
	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		MLog retVal = new MLog();
		
		String attr;
		String valueAsString;
		Object value;
		ConverterMatcher converter;
		for(Map.Entry<String, AttributeHandler> e : getRegister().entrySet()) {
			attr = e.getKey();
			
			valueAsString = reader.getAttribute(attr);
			
			converter = e.getValue().getConverter();
			if(converter != null) {
				if(converter instanceof SingleValueConverter) {
					value = ((SingleValueConverter) converter).fromString(valueAsString);
				}
				else if(converter instanceof Converter) {
					reader.moveDown();
					value = ((Converter) converter).unmarshal(reader, context);
					reader.moveUp();
				}
				else {
					throw new IllegalStateException("Wrong converter type!");
				}
			}
			else {
				value = valueAsString;
			}
			
			try {
				BeanUtils.setProperty(retVal, e.getValue().getBeanAttributeName(), value);
			}
			catch(Exception ex) {
				getLog().error("Colud not to put attribute!", ex);
				throw new IllegalStateException("Colud not to put attribute!", ex);
			}
		}
		
		return retVal;
	}
}
