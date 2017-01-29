package model.dao.interfaces;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.db.hib.util.HibernateUtil;

/***
 * Abstract class implemented basic interface using for basic operations on
 * database. All DAO objects should extends this class.
 * 
 * @author Mateusz Mucha
 *
 * @param <T>
 *            entity type
 * @param <ID>
 *            identificator type
 */
public abstract class GenericDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
	protected Class<? extends T> daoClassType;

	public GenericDao() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parametrized = (ParameterizedType) type;
		this.daoClassType = (Class) parametrized.getActualTypeArguments()[0];
	}

	public int save(T entity) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();

			return 1;
		} catch (Exception e) {
			e.getStackTrace();

			return 0;
		} finally {
			session.clear();
			session.close();
		}
	}

	public int update(T entity) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();

			return 1;
		} catch (Exception e) {
			e.getStackTrace();

			return 0;
		} finally {
			session.clear();
			session.close();
		}
	}

	public int delete(T entity) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();

			return 1;
		} catch (Exception e) {
			e.getStackTrace();

			return 0;
		} finally {
			session.clear();
			session.close();
		}
	}

	public int deleteAll(List<T> entities) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = session.beginTransaction();
			for (T entity : entities) {
				session.delete(entity);
			}
			transaction.commit();

			return 1;
		} catch (Exception e) {
			e.getStackTrace();

			return 0;
		} finally {
			session.clear();
			session.close();
		}
	}

	public T findById(ID id) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T found = (T) session.get(daoClassType, id);

		if (found != null) {
			return found;
		} else {
			return null;
		}
	}

	public List<T> findAll(Class<T> clazz) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(clazz);

		return criteria.list();
	}
}