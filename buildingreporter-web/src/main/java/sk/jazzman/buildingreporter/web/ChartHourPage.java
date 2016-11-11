/**
 *
 */
package sk.jazzman.buildingreporter.web;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.AxisType;
import com.googlecode.wickedcharts.highcharts.options.HorizontalAlignment;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.LegendLayout;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotLine;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.VerticalAlignment;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.series.Series;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.googlecode.wickedcharts.wicket7.highcharts.Chart;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.measurement.MLog;
import sk.jazzman.buildingreporter.repository.measurement.MLogRepository;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author jano
 */
public class ChartHourPage extends PageAbt {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	protected MLogRepository mlogRepository;
	
	@Override
	public IModel<String> newPageTitleModel() {
		return Model.of("Priebeh teplot za hodinu");
	}
	
	@Override
	protected void construct() {
		super.construct();
		
		add(new DayChartForm("form"));
	}
	
	/**
	 * DayChartForm form
	 *
	 * @author jano
	 */
	protected class DayChartForm extends Form<Void> {
		/** serial id */
		private static final long serialVersionUID = 1L;
		
		/**
		 * {@link Constructor}
		 *
		 * @param id
		 */
		public DayChartForm(String id) {
			super(id);
			
			construct();
		}
		
		/**
		 * Construct form
		 */
		protected void construct() {
			add(newChart("chart"));
		}
		
		protected Component newChart(String id) {
			Options options = new Options();
			options.setTitle(new Title("Priebeh"));
			
			int size = 60;
			int step = 1;
			
			Map<Long, List<Number>> temperature = new TreeMap<Long, List<Number>>();
			
			LocalDateTime dt = LocalDateTime.now();
			dt = dt.minusHours(1);
			
			Map<Long, List<MLog>> data = new TreeMap<Long, List<MLog>>();
			
			// FIXME:
			Long[] items = new Long[] { Long.valueOf(11), Long.valueOf(12), Long.valueOf(13), Long.valueOf(14) };
			
			//			for(Long itemId : items) {
			//				data.put(itemId, MLog.createCriteria()//
			//					.add(Restrictions.eq("item.id", itemId))//
			//					.addOrder(Order.asc("logDate"))//
			//					.add(Restrictions.ge("logDate", new Timestamp(dt.getMillis()))).list());
			//			}
			
			for(Long itemId : items) {
				data.put(itemId, mlogRepository.findLogsGreaterThenDate(new Timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()), itemId));
			}
			
			LocalDateTime min = LocalDateTime.from(dt);
			List<Number> tList;
			for(int index = 0; index < size; index++) {
				for(Long itemId : items) {
					tList = temperature.get(itemId);
					if(tList == null) {
						tList = new ArrayList<Number>(size);
						temperature.put(itemId, tList);
					}
					
					calcutateTemperature(index, min, step, data.get(itemId), tList);
				}
				
				min = min.plusMinutes(step);
			}
			
			Series<Number> s;
			
			for(Map.Entry<Long, List<Number>> e : temperature.entrySet()) {
				s = new SimpleSeries();
				s.setData(e.getValue());
				s.setName(BPart.findBPart(e.getKey()).getName());
				
				s.setPointInterval(60000);
				s.setPointStart(dt.getMillis());
				options.addSeries(s);
			}
			
			options.setxAxis(new Axis().setType(AxisType.DATETIME));
			
			PlotLine plotLines = new PlotLine();
			plotLines.setValue(0f);
			plotLines.setWidth(1);
			plotLines.setColor(new HexColor("#999999"));
			
			Axis yAxis = new Axis();
			yAxis.setTitle(new Title("Temperature (Celsius)"));
			yAxis.setPlotLines(Collections.singletonList(plotLines));
			options.setyAxis(yAxis);
			
			Legend legend = new Legend();
			legend.setLayout(LegendLayout.VERTICAL);
			legend.setAlign(HorizontalAlignment.RIGHT);
			legend.setVerticalAlign(VerticalAlignment.TOP);
			legend.setX(-10);
			legend.setY(100);
			legend.setBorderWidth(0);
			options.setLegend(legend);
			
			return new Chart("chart", options);
		}
		
		/**
		 * Calculate temperature
		 *
		 * @param index
		 * @param minute
		 * @param data
		 * @param temperature
		 */
		private void calcutateTemperature(int index, LocalDateTime minute, int step, List<MLog> data, List<Number> temperature) {
			if(temperature == null) {
				throw new IllegalArgumentException("Null argument!");
			}
			
			Double avg;
			
			if(CollectionUtils.isNotEmpty(data)) {
				
				final long min = minute.getMillis();
				final long max = minute.plusMinutes(step).getMillis();
				
				List<MLog> filtered = data.stream().filter(d -> {
					long t = ((MLog) d).getLogDate().getTime();
					
					return t >= min && t < max;
				}).collect(Collectors.toList());
				
				if(CollectionUtils.isNotEmpty(filtered)) {
					int count = 0;
					Double sum = Double.valueOf(0);
					
					for(MLog l : filtered) {
						sum += l.getValueMeasured();
						count++;
					}
					
					avg = sum / count;
				}
				else {
					avg = null;
				}
			}
			else {
				avg = null;
			}
			
			if(avg == null) {
				avg = Double.valueOf(0d);
			}
			
			temperature.add(avg);
		}
	}
}
