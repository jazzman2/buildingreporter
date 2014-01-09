/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.measurement.MLog;
import sk.jazzman.buildingreporter.domain.measurement.MLogInf;
import sk.jazzman.buildingreporter.domain.measurement.MLogUtils;

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
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;

/**
 * @author jano
 * 
 */
public class ChartWeekPage extends PageAbt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IModel<String> newPageTitleModel() {
		return Model.of("Tyzdenny priebeh teplot");
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
	 * 
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

			int size = 7 * 24;
			int step = 1;

			Map<Long, List<Number>> temperature = new TreeMap<Long, List<Number>>();

			DateTime dt = new DateTime(System.currentTimeMillis());
			dt = dt.minusWeeks(1);

			Map<Long, List<MLogInf>> data = new TreeMap<Long, List<MLogInf>>();

			// FIXME:
			Long[] items = new Long[] { Long.valueOf(11), Long.valueOf(12), Long.valueOf(13), Long.valueOf(14) };

			for (Long itemId : items) {
				data.put(itemId, MLog.createCriteria()//
						.add(Restrictions.eq("item.id", itemId))//
						.addOrder(Order.desc("logDate"))//
						.add(Restrictions.ge("logDate", new Timestamp(dt.getMillis()))).list());
			}

			DateTime h = new DateTime(dt);
			List<Number> tList;
			for (int index = 0; index < size; index++) {

				for (Long itemId : items) {
					tList = temperature.get(itemId);
					if (tList == null) {
						tList = new ArrayList<Number>(size);
						temperature.put(itemId, tList);
					}

					MLogUtils.calcutateAverageHours(h, step, data.get(itemId), tList);
				}

				h = h.plusHours(step);
			}

			Series<Number> s;

			for (Map.Entry<Long, List<Number>> e : temperature.entrySet()) {
				s = new SimpleSeries();
				s.setData(e.getValue());
				s.setName(BPart.findBPart(e.getKey()).getName());

				s.setPointInterval(3600000);
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

	}
}
