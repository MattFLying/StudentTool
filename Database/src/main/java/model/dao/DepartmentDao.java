package model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IDepartmentDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;

public class DepartmentDao extends GenericDao<Department, Integer> implements IDepartmentDao {
	private Department departmentEntity;
	
	
	public DepartmentDao() {
		this.departmentEntity = null;
	}
	

	public Department findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentName", name));
			departmentEntity = (Department)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(departmentEntity != null) {
			return departmentEntity;
		} else {
			return null;
		}
	}
	public Department findByFullName(String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentDescription", description));
			departmentEntity = (Department)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(departmentEntity != null) {
			return departmentEntity;
		} else {
			return null;
		}
	}
}