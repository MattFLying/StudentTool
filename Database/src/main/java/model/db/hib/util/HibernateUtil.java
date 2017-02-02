package model.db.hib.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/***
 * Basic class allows to connection with database using Hibernate.
 * 
 * @author Mateusz Mucha
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

	public static void closeSessionFactory() {
		sessionFactory.close();
	}
}