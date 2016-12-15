package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.dao.DepartmentDao;
import model.dao.FieldOfStudyDao;
import model.dao.interfaces.IFieldOfStudyDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;
import model.entity.FieldOfStudy;

public class FieldOfStudyService extends GenericDaoService<FieldOfStudyDao, IFieldOfStudyDao> {
	private FieldOfStudyDao field = null;
	
	
	public FieldOfStudyService() {
		this.field = new FieldOfStudyDao();
	}
	
	
	@Override
	public FieldOfStudyDao getDao() {
		return field;
	}
	@Override
	public IFieldOfStudyDao getDaoInterface() {
		return field;
	}
	public FieldOfStudy getByName(String name) {
		return field.findByName(name);
	}
	public List<FieldOfStudy> getAllByDepartmentId(Integer value) {
		return field.findByDepartmentId(value);
	}
	public List<FieldOfStudy> getAllByDepartmentShortName(String name) {
		return field.findByDepartmentName(name);
	}
	public List<FieldOfStudy> getAllByDepartmentFullName(String name) {
		return field.findByDepartmentFullName(name);
	}
	public void updateName(Integer fieldOfStudyId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		FieldOfStudy entity = null;
		
		try {
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", fieldOfStudyId));
			entity = (FieldOfStudy)criteria.list().get(0);
			
			entity.setFieldOfStudyName(newValue);
			
			field.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateDepartmentById(Integer fieldOfStudyId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		FieldOfStudy entity = null;
		
		try {
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", fieldOfStudyId));
			entity = (FieldOfStudy)criteria.list().get(0);
			
			entity.setDepartmentId(newValue);
			
			field.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateDepartmentByShortName(Integer fieldOfStudyId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		FieldOfStudy entity = null;
		
		try {
			Department department = new DepartmentDao().findByName(newValue);
			
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", fieldOfStudyId));
			entity = (FieldOfStudy)criteria.list().get(0);
			
			entity.setDepartmentId(department.getDepartmentId());
			
			field.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}