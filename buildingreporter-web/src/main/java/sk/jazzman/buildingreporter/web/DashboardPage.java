/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;

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

	/** Serial id */
	private static final long serialVersionUID = 1L;

	// @SpringBean(name = "serialization")
	// XStreamManager sm;
	//
	// @SpringBean(name = "configuration")
	// Configuration cfg;
	//
	// @SpringBean(name = "test")
	// @Autowired
	// TestBean test;

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
	}
}
