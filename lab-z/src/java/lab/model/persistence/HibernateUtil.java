/**
 * 
 */
package lab.model.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author jromero
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
	    // Alternatively, you could look up in JNDI here
	    return sessionFactory;
	}

	public static void shutdown() {
	    // Close caches and connection pools
	    getSessionFactory().close();
	}

}
