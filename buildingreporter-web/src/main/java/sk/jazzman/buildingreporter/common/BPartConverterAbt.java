/**
 * 
 */
package sk.jazzman.buildingreporter.common;

import org.apache.commons.configuration.Configuration;

import sk.jazzman.buildingreporter.domain.building.BPart;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author jano
 * 
 */
public abstract class BPartConverterAbt extends AbstractSingleValueConverter {

	public abstract Configuration getConfiguration();

	@Override
	public boolean canConvert(Class type) {
		return BPart.class.equals(type);
	}

	@Override
	public Object fromString(String str) {
		BPart retVal;

		if (str == null || "".equals(str.trim())) {
			retVal = null;
		} else {
			Long bPartId = getConfiguration().getLong("sensors/sensor[id='" + str + "']/bpart", null);

			if (bPartId == null) {
				retVal = null;
			} else {
				retVal = BPart.findBPart(bPartId);
			}
		}

		return retVal;
	}
}
