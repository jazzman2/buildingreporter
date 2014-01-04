/**
 * 
 */
package sk.jazzman.buildingreporter.common;

import org.apache.commons.configuration.Configuration;

import sk.jazzman.buildingreporter.domain.measurement.MUnit;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author jano
 * 
 */
public abstract class UnitTypeConverterAbt extends AbstractSingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return MUnit.class.equals(type);
	}

	public abstract Configuration getConfiguration();

	@Override
	public Object fromString(String str) {
		MUnit mUnit;
		if (str == null) {
			mUnit = null;
		} else {

			Long mUnitId = getConfiguration().getLong("munits/unit[name='" + str + "']/value", null);

			if (mUnitId == null) {
				throw new IllegalArgumentException("Missing configuration for value=" + str);
			}

			mUnit = MUnit.findMUnit(mUnitId);

			if (mUnit == null) {
				throw new IllegalStateException("MUnit does not exist!");
			}
		}

		return mUnit;
	}
}
