/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import java.lang.reflect.Constructor;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Abstract Web Page
 * 
 * @author jano
 * 
 */
public abstract class PageAbt extends WebPage {

	/** Serial id */
	private static final long serialVersionUID = 1L;

	private IModel<String> headingModel;

	/**
	 * {@link Constructor}
	 * 
	 */
	public PageAbt() {
		super();

		setOutputMarkupId(true);

		initPage();

		construct();
	}

	public abstract IModel<String> newPageTitleModel();

	/**
	 * Init Page
	 */
	private void initPage() {

		add(createHeading("heading"));

		add(new AjaxFallbackLink<String>("aktualneTeploty") {
			/** serial id */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ActualTemperaturePage.class);
			}
		});

		add(new AjaxFallbackLink<String>("denPriebeh") {
			/** serial id */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ChartDayPage.class);
			}
		});

		add(new AjaxFallbackLink<String>("hodinaPriebeh") {
			/** serial id */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ChartHourPage.class);
			}
		});

		add(new AjaxFallbackLink<String>("tyzdenPriebeh") {
			/** serial id */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ChartWeekPage.class);
			}
		});
	}

	/**
	 * Construct Web Page
	 */
	protected void construct() {

	}

	protected Component createHeading(String id) {
		headingModel = newHeadingModel();
		return new Label(id, headingModel);
	}

	protected IModel<String> newHeadingModel() {
		return newPageTitleModel();
	}

	// protected Component createPageTitle(String id) {
	// return new Label(id, newPageTitleModel());
	// }

}
