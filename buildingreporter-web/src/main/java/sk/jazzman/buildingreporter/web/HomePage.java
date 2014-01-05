/**
 * 
 */
package sk.jazzman.buildingreporter.web;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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

	}

	@Override
	public IModel<String> newPageTitleModel() {
		return Model.of("HomePage");
	}
}
