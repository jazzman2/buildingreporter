/**
 * 
 */
package sk.jazzman.brmi.jpa;

import java.lang.reflect.Constructor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		try {
			// Configuration cfg = new Configuration().configure();
			// serviceRegistry = new
			// ServiceRegistryBuilder().applySettings(cfg.getImports()).buildServiceRegistry();
			serviceRegistry = new ServiceRegistryBuilder().configure().buildServiceRegistry();
			MetadataSources metadataSources = new MetadataSources(serviceRegistry);
			sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

			isInitialized = true;
		} catch (Throwable t) {
			getLogger().error("Could not to init service manager.", t);
		}
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
	private synchronized boolean isInitialized() {
		return isInitialized;
	}
}
