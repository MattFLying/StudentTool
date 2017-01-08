package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IFieldOfStudyDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;
import model.entity.FieldOfStudy;
import model.entity.Group;

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
	public FieldOfStudy findFieldOfStudyNameById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		FieldOfStudy entity = null;
		
		try {
			List results = session.createCriteria(FieldOfStudy.class)
					.add(Restrictions.eq("fieldOfStudyId", id))
					.setProjection( Projections.projectionList()
					.add( Projections.property("fieldOfStudyName"), "fieldOfStudyName") )
					.setResultTransformer(Transformers.aliasToBean(FieldOfStudy.class))
					.list();

			entity = (FieldOfStudy)results.get(0);			
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(entity != null) {
			return entity;
		} else {
			return null;
		}
	}
	public FieldOfStudy findFieldOfStudyIdByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		FieldOfStudy entity = null;
		
		try {
			List results = session.createCriteria(FieldOfStudy.class)
					.add(Restrictions.eq("fieldOfStudyName", name))
					.setProjection( Projections.projectionList()
					.add( Projections.property("fieldOfStudyId"), "fieldOfStudyId") )
					.setResultTransformer(Transformers.aliasToBean(FieldOfStudy.class))
					.list();

			entity = (FieldOfStudy)results.get(0);			
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(entity != null) {
			return entity;
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
	public List<FieldOfStudy> findAllFields() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			list = session.createCriteria(FieldOfStudy.class).list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}