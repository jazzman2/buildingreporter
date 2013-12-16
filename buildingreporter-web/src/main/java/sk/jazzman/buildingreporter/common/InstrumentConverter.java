/**
 * 
 */
package sk.jazzman.buildingreporter.common;

import sk.jazzman.buildingreporter.domain.instrument.InstrumentInf;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Instrument {@link Converter}
 * 
 * @author jano
 * 
 */
public class InstrumentConverter extends EntityConverterAbt {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canConvert(Class type) {
		return InstrumentInf.class.equals(type);
	}

	@Override
	protected void registerAttributeHandlers() {
		// TODO Auto-generated method stub

	}

}
