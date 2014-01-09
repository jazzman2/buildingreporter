/**
 * 
 */
package sk.jazzman.buildingreporter.domain.measurement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;

/**
 * {@link MLogInf} utilities
 * 
 * @author jkovalci
 * 
 */
public final class MLogUtils {
	private MLogUtils() {

	}

	/**
	 * Calculate average temperature in hours
	 * 
	 * @param index
	 * @param minute
	 * @param data
	 * @param temperature
	 */
	public static void calcutateAverageHours(DateTime hour, int step, List<MLogInf> data, List<Number> temperature) {
		if (temperature == null) {
			throw new IllegalArgumentException("Null argument!");
		}

		Double avg;

		if (CollectionUtils.isNotEmpty(data)) {

			final long min = hour.getMillis();
			final long max = hour.plusHours(step).getMillis();

			List<MLogInf> filtered = new ArrayList<MLogInf>();

			for (MLogInf l : data) {
				long d = l.getLogDate().getTime();

				if (d < min) {
					continue;
				} else if (d > max) {
					break;
				} else {
					filtered.add(l);
				}
			}

			if (CollectionUtils.isNotEmpty(filtered)) {
				int count = 0;
				double sum = 0d;

				for (MLogInf l : filtered) {
					sum += l.getValueMeasured();
					count++;
				}

				avg = sum / count;
			} else {
				avg = null;
			}
		} else {
			avg = null;
		}

		if (avg == null) {
			avg = Double.valueOf(0d);
		}

		temperature.add(avg);
	}
}
