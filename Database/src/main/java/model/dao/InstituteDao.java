package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IInstituteDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;
import model.entity.FieldOfStudy;
import model.entity.Group;
import model.entity.Institute;

public class InstituteDao extends GenericDao<Institute, Integer> implements IInstituteDao {
	private Institute instituteEntity;
	
	
	public InstituteDao() {
		this.instituteEntity = new Institute();
	}
	
	

	public Institute findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Institute.class);
			criteria.add(Restrictions.eq("instituteName", name));
			instituteEntity = (Institute)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(instituteEntity != null) {
			return instituteEntity;
		} else {
			return null;
		}
	}
	public Institute findByFullName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Institute.class);
			criteria.add(Restrictions.eq("instituteDescription", name));
			instituteEntity = (Institute)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(instituteEntity != null) {
			return instituteEntity;
		} else {
			return null;
		}
	}
	public List<Institute> findByDepartmentId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Institute> list = new ArrayList<Institute>();
		
		try {
			criteria = session.createCriteria(Institute.class);
			criteria.add(Restrictions.eq("departmentId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Institute> findByDepartmentName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Institute> list = new ArrayList<Institute>();
		
		try {
			Department department = new DepartmentDao().findByName(name);
			
			criteria = session.createCriteria(Institute.class);
			criteria.add(Restrictions.eq("departmentId", department.getDepartmentName()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	public Institute findInstituteNameById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Institute entity = null;
		
		try {
			List results = session.createCriteria(Institute.class)
					.add(Restrictions.eq("instituteId", id))
					.setProjection( Projections.projectionList()
					.add( Projections.property("instituteName"), "instituteName") )
					.setResultTransformer(Transformers.aliasToBean(Institute.class))
					.list();

			entity = (Institute)results.get(0);			
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
	public Institute findInstituteIdByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Institute entity = null;
		
		try {
			List results = session.createCriteria(Institute.class)
					.add(Restrictions.eq("instituteName", name))
					.setProjection( Projections.projectionList()
					.add( Projections.property("instituteId"), "instituteId") )
					.setResultTransformer(Transformers.aliasToBean(Institute.class))
					.list();

			entity = (Institute)results.get(0);			
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
}