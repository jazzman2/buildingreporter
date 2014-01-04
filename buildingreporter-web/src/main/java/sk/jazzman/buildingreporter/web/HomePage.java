/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;

/**
 * @author jano
 * 
 */
public class HomePage extends PageAbt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void construct() {
		super.construct();

		add(new AjaxFallbackLink<String>("link") {
			/** serial id */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(DashboardPage.class);
			}
		});
	}
}
