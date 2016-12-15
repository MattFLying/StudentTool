package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IFieldOfStudyDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;
import model.entity.FieldOfStudy;

public class FieldOfStudyDao extends GenericDao<FieldOfStudy, Integer> implements IFieldOfStudyDao {
	private FieldOfStudy fieldEntity;
	
	
	public FieldOfStudyDao() {
		this.fieldEntity = null;
	}
	

	public FieldOfStudy findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("fieldOfStudyName", name));
			fieldEntity = (FieldOfStudy)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(fieldEntity != null) {
			return fieldEntity;
		} else {
			return null;
		}
	}
	public List<FieldOfStudy> findByDepartmentId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("departmentId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<FieldOfStudy> findByDepartmentName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			Department department = new DepartmentDao().findByName(name);
			
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("departmentId", department.getDepartmentId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<FieldOfStudy> findByDepartmentFullName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			Department department = new DepartmentDao().findByFullName(name);
			
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("departmentId", department.getDepartmentId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
}