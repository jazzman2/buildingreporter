/**
 *
 */
package sk.jazzman.buildingreporter.domain.measurement;

import sk.jazzman.buildingreporter.repository.measurement.MLogRepository;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author jano
 */
public class MLogUtils2 {
	private MLogUtils2() {
		
	}
	
	/**
	 * Calculate average temperature in hours
	 * <p>
	 * Musia byt data zotriedene podla datumu!!!
	 *
	 * @param start
	 * @param stop
	 * @param stepHour
	 * @param data
	 * @param temperature
	 */
	public static List<Number> calcutateAverageHours(LocalDateTime start, LocalDateTime stop, int stepHour, Long itemId, MLogRepository mlogRepository) {
		
		double avg;
		double avgSum;
		int avgCount;
		
		//		int intervalSize = Hours.hoursBetween(start, stop).getHours();
		long intervalSize = Duration.between(start, stop).toHours();
		
		if(intervalSize > Integer.MAX_VALUE) {
			throw new IllegalStateException("Interval to long!");
		}
		
		LocalDateTime min;
		LocalDateTime max;
		
		MLog log;
		LocalDateTime logDate;
		double logValue;
		
		List<Number> temperature = new ArrayList<>((int) intervalSize);
		List<MLog> data;
		
		for(int index = 0; index < intervalSize; index++) {
			
			min = start.plusHours(index * stepHour);
			max = start.plusHours((index + 1) * stepHour);
			
			data = MLog.findLogsForHour(new Timestamp(min), itemId);
			
			ListIterator<MLog> iterator = data.listIterator();
			
			avg = 0d;
			avgCount = 0;
			avgSum = 0d;
			
			while(iterator.hasNext()) {
				log = iterator.next();
				logDate = log.getLogDate().toLocalDateTime();
				logValue = log.getValueMeasured();
				
				//				if(logDate < min) {
				if(logDate.isBefore(min)) {
					continue;
				}
				//				else if(logDate > max) {
				else if(logDate.isAfter(max)) {
					iterator.previous();
					break;
				}
				else {
					avgCount++;
					avgSum += logValue;
				}
			}
			
			if(avgCount > 0) {
				avg = avgSum / avgCount;
			}
			else {
				avg = 0;
			}
			
			temperature.add(Double.valueOf(avg));
		}
		
		return temperature;
	}
}
