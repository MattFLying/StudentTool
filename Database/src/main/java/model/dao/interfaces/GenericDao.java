package model.dao.interfaces;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.db.hib.util.HibernateUtil;

public abstract class GenericDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
	protected Class<? extends T> daoClassType;
	
	
	public GenericDao() {
		Type type = getClass().getGenericSuperclass();
        ParameterizedType parametrized = (ParameterizedType)type;
        this.daoClassType = (Class) parametrized.getActualTypeArguments()[0];
	}
	
	
	public void save(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
			
		try {
			Transaction transaction = session.beginTransaction();
			session.save( entity );			
			transaction.commit();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		} 
	}
	public void update(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			session.update( entity );			
			transaction.commit();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		} 
	}
	public void delete(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			session.delete( entity );			
			transaction.commit();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		} 
	}
	public void deleteAll(List<T> entities) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			for(T entity : entities) {
				session.delete( entity );	
			}		
			transaction.commit();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		} 
	}
	public T findById(ID id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T found = (T)session.get(daoClassType, id);
		
		if(found != null) {
			return found;
		} else {
			return null;
		}
	}
	public List<T> findAll(Class<T> clazz) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Criteria criteria = session.createCriteria(clazz);
	    return criteria.list();
	}
}