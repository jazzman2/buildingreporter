/**
 * 
 */
package sk.jazzman.buildingreporter.common;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import sk.jazzman.buildingreporter.domain.measurement.MUnit;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author jano
 * 
 */
public class UnitTypeConverter extends AbstractSingleValueConverter {

	@Autowired
	private Configuration configuration;

	@Override
	public boolean canConvert(Class type) {
		return MUnit.class.equals(type);
	}

	@Override
	public Object fromString(String str) {
		MUnit mUnit;
		if (str == null) {
			mUnit = null;
		} else {

			Long mUnitId = configuration.getLong("munits/unit[name=" + str + "]/value", null);

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
