/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.configuration.Configuration;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.measurement.MLog;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.series.Series;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;

/**
 * Dashboard Page
 * 
 * @author jkovalci
 * 
 */
public class DashboardPage extends PageAbt {

	@SpringBean(name = "configuration")
	private Configuration configuration;

	/** Serial id */
	private static final long serialVersionUID = 1L;

	@Override
	protected void construct() {
		super.construct();

		add(new DashboardForm("form"));
	}

	/**
	 * Dashboard form
	 * 
	 * @author jano
	 * 
	 */
	protected class DashboardForm extends Form<Void> {
		/** serial id */
		private static final long serialVersionUID = 1L;

		/**
		 * {@link Constructor}
		 * 
		 * @param id
		 */
		public DashboardForm(String id) {
			super(id);

			construct();
		}

		/**
		 * Construct form
		 */
		protected void construct() {
			add(newChart("chart"));
			add(newAktualneTeploty("actualtemperature"));
		}

		protected Component newChart(String id) {
			Options options = new Options();
			options.setTitle(new Title("Test"));

			List<String> hours = new ArrayList<String>(24);
			List<Number> temperature = new ArrayList<Number>(24);
			Random r = new Random();
			for (int index = 0; index < 24; index++) {
				hours.add(Integer.valueOf(index).toString());
				temperature.add((r.nextDouble() * 20.0d) - 10.0d);
			}
			options.setxAxis(new Axis().setCategories(hours));

			List<Series<?>> series = new ArrayList<Series<?>>();

			series.add(new SimpleSeries()//
					.setName("Kotolna")//
					.setData(temperature));

			options.setSeries(series);

			return new Chart("chart", options);
		}

		protected Component newAktualneTeploty(String id) {

			List<MLog> data = new ArrayList<MLog>();
			MLog l;

			// FIXME: do konfiguracie presunut co zobrazovat
			for (Long itemId : new Long[] { Long.valueOf(-1), Long.valueOf(4), Long.valueOf(5) }) {
				l = (MLog) MLog.createCriteria()//
						.add(Restrictions.eq("item.id", itemId))//
						.addOrder(Order.desc("logDate"))//
						.setMaxResults(1).uniqueResult();

				if (l == null) {
					l = new MLog();
					l.setItem(BPart.findBPart(itemId));
				}

				data.add(l);
			}

			ListDataProvider<MLog> dp = new ListDataProvider<MLog>(data);

			DataView<MLog> dataView = new DataView<MLog>(id, dp) {
				/** Serial id */
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<MLog> item) {
					item.add(new Label("item.name", item.getModelObject().getItem().getName()));

					Object value = item.getModelObject().getValueMeasured();
					item.add(new Label("mlog.value_measured", value != null ? value.toString() : ""));

					value = item.getModelObject().getLogDate();
					item.add(new Label("mlog.log_date", value != null ? value.toString() : ""));
				}
			};

			return dataView;
		}
	}
}
