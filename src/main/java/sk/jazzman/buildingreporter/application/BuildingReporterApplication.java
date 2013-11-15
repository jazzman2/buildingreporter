/**
 * 
 */
package sk.jazzman.buildingreporter.application;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import sk.jazzman.buildingreporter.web.DashboardPage;

/**
 * @author jkovalci
 * 
 */
@Component(value = "application")
public class BuildingReporterApplication extends WebApplication implements Serializable, ApplicationContextAware {
	/** Serial id */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));

		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
 
                  getMarkupSettings().setStripWicketTags(true);
            getMarkupSettings().setStripComments(true);
            getMarkupSettings().setCompressWhitespace(true);
        
	}
	
	/**
	 * Application getter
	 * 
	 * @return
	 */
	public static BuildingReporterApplication get() {
		return (BuildingReporterApplication) WebApplication.get();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return DashboardPage.class;
	}
}
