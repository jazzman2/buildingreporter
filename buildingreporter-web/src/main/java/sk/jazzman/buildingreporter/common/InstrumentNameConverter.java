/**
 * 
 */
package sk.jazzman.buildingreporter.common;

import org.hibernate.criterion.Restrictions;

import sk.jazzman.buildingreporter.domain.instrument.Instrument;
import sk.jazzman.buildingreporter.domain.instrument.InstrumentInf;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * Convert Instrument Name to {@link InstrumentInf}
 * 
 * @author jano
 * 
 */
public class InstrumentNameConverter extends AbstractSingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return InstrumentNameConverter.class.equals(type);
	}

	@Override
	public Object fromString(String instrumentName) {
		InstrumentInf retVal;

		if (instrumentName == null) {
			retVal = null;
		} else {
			retVal = (InstrumentInf) Instrument.createCriteria().add(Restrictions.eq("name", instrumentName)).uniqueResult();
		}

		return retVal;
	}

}
