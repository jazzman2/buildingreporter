/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.jazzman.brmi.domain.measurement.MLog;

/**
 * @author jano
 * 
 */
public class SessionManager {
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;

	private Session session;

	private boolean isActiveSession = false;
	private boolean isInitialized = false;

	/**
	 * {@link Constructor}
	 * 
	 */
	public SessionManager() {
		ensureInitialize();
	}

	/**
	 * Getter {@link Logger}
	 * 
	 * @return
	 */
	private Logger getLogger() {
		return logger;
	}

	/**
	 * Ensure initialization Session Manager
	 */
	private synchronized void ensureInitialize() {

		Properties hProp;
		try {
			hProp = loadHibernateProperties("hibernate.properties");
		} catch (Exception ex) {
			hProp = null;
			getLogger().error("Could not to load properties!", ex);
		}

		if (hProp != null) {
			try {

				org.hibernate.cfg.Configuration hCfg = new org.hibernate.cfg.Configuration();
				hCfg.setProperties(hProp);
				hCfg.addAnnotatedClass(MLog.class);

				serviceRegistry = new ServiceRegistryBuilder().applySettings(hCfg.getProperties()).buildServiceRegistry();

				sessionFactory = hCfg.buildSessionFactory(serviceRegistry);

				isInitialized = true;
			} catch (Throwable t) {
				getLogger().error("Could not to init service manager.", t);
			}
		} else {
			isInitialized = false;
		}
	}

	private Properties loadHibernateProperties(String fileName) throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("hibernate.properties");
		Properties hibernateProperties = new Properties();
		hibernateProperties.load(inputStream);

		return hibernateProperties;
	}

	/**
	 * Getter {@link SessionFactory}
	 * 
	 * @return
	 */
	protected synchronized SessionFactory getSessionFactory() {
		return isInitialized() ? sessionFactory : null;
	}

	/**
	 * Close session
	 */
	public synchronized void close() {
		getSessionFactory().close();
		isInitialized = false;
	}

	/**
	 * Return {@link Session}
	 * 
	 * @return
	 */
	public synchronized Session getSession() {
		ensureInitializeSession();

		return isSessionActive() ? session : null;
	}

	/**
	 * Ensure initialize session
	 */
	private synchronized void ensureInitializeSession() {
		if (isInitialized()) {
			session = getSessionFactory().openSession();
			isActiveSession = true;
		} else {
			isActiveSession = false;
		}
	}

	/**
	 * ? true if session is active
	 * 
	 * @return
	 */
	private synchronized boolean isSessionActive() {
		return isActiveSession;
	}

	/**
	 * ? true if is initialized
	 * 
	 * @return
	 */
	protected synchronized boolean isInitialized() {
		return isInitialized;
	}
}
