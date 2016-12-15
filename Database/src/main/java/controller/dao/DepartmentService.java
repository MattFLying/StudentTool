package controller.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.DepartmentDao;
import model.dao.interfaces.IDepartmentDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;

public class DepartmentService extends GenericDaoService<DepartmentDao, IDepartmentDao> {
	private DepartmentDao department = null;
	
	
	public DepartmentService() {
		this.department = new DepartmentDao();
	}
	
	
	@Override
	public DepartmentDao getDao() {
		return department;
	}
	@Override
	public IDepartmentDao getDaoInterface() {
		return department;
	}
	public Department getByName(String name) {
		return department.findByName(name);
	}
	public Department getByFullName(String name) {
		return department.findByFullName(name);
	}
	public void updateShortName(Integer departmentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Department entity = null;
		
		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentId", departmentId));
			entity = (Department)criteria.list().get(0);
			
			entity.setDepartmentName(newValue);
			
			department.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFullName(Integer departmentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Department entity = null;
		
		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentId", departmentId));
			entity = (Department)criteria.list().get(0);
			
			entity.setDepartmentDescription(newValue);
			
			department.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateBuilding(Integer departmentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Department entity = null;
		
		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentId", departmentId));
			entity = (Department)criteria.list().get(0);
			
			entity.setDepartmentBuilding(newValue);
			
			department.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}