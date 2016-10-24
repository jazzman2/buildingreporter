/**
 * 
 */
package sk.jazzman.buildingreporter.domain.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * {@link MLogInf} utilities
 * 
 * @author jkovalcik
 * 
 */
public final class MLogUtils {

	private MLogUtils() {

	}

	/**
	 * Calculate average temperature in hours
	 * 
	 * Musia byt data zotriedene podla datumu!!!
	 * 
	 * @param start
	 * @param stop
	 * @param stepHour
	 * @param data
	 * @param temperature
	 */
	public static List<Number> calcutateAverageHours(LocalDateTime start, DateTime stop, int stepHour, List<MLogInf> data) {

		double avg;
		double avgSum;
		int avgCount;

		int intervalSize = Hours.hoursBetween(start, stop).getHours();

		long min;
		long max;

		ListIterator<MLogInf> iterator = data.listIterator();

		MLogInf log;
		long logDate;
		double logValue;

		List<Number> temperature = new ArrayList<Number>(intervalSize);

		for (int index = 0; index < intervalSize; index++) {
			min = start.plusHours(index * stepHour).getMillis();
			max = start.plusHours((index + 1) * stepHour).getMillis();

			avg = 0d;
			avgCount = 0;
			avgSum = 0d;

			while (iterator.hasNext()) {
				log = iterator.next();
				logDate = log.getLogDate().getTime();
				logValue = log.getValueMeasured();

				if (logDate < min) {
					continue;
				} else if (logDate > max) {
					iterator.previous();
					break;
				} else {
					avgCount++;
					avgSum += logValue;
				}
			}

			if (avgCount > 0) {
				avg = avgSum / avgCount;
			} else {
				avg = 0;
			}

			temperature.add(Double.valueOf(avg));
		}

		return temperature;
	}

}
