/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sk.jazzman.buildingreporter.domain.building.BPart;
import sk.jazzman.buildingreporter.domain.measurement.MLog;

/**
 * ActualTemperaturePage Page
 * 
 * @author jkovalci
 * 
 */
public class ActualTemperaturePage extends PageAbt {

	@SpringBean(name = "configuration")
	private Configuration configuration;

	/** Serial id */
	private static final long serialVersionUID = 1L;

	@Override
	protected void construct() {
		super.construct();

		add(new ActualTemperatureForm("form"));
	}

	@Override
	public IModel<String> newPageTitleModel() {
		return Model.of("AktualneTeploty");
	}

	/**
	 * Dashboard form
	 * 
	 * @author jano
	 * 
	 */
	protected class ActualTemperatureForm extends Form<Void> {
		/** serial id */
		private static final long serialVersionUID = 1L;

		/**
		 * {@link Constructor}
		 * 
		 * @param id
		 */
		public ActualTemperatureForm(String id) {
			super(id);

			construct();
		}

		/**
		 * Construct form
		 */
		protected void construct() {
			add(newAktualneTeploty("actualtemperature"));
		}

		protected Component newAktualneTeploty(String id) {

			List<MLog> data = new ArrayList<MLog>();
			MLog l;

			// FIXME:
			Long[] items = new Long[] { Long.valueOf(11), Long.valueOf(12), Long.valueOf(13), Long.valueOf(14) };

			// FIXME: do konfiguracie presunut co zobrazovat
			for (Long itemId : items) {
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
