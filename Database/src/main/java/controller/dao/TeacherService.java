package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.TeacherDao;
import model.dao.interfaces.ITeacherDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Certificate;
import model.entity.Course;
import model.entity.Teacher;

public class TeacherService extends GenericDaoService<TeacherDao, ITeacherDao> {
	private TeacherDao teacher = null;
	
	
	public TeacherService() {
		this.teacher = new TeacherDao();
	}


	@Override
	public TeacherDao getDao() {
		return teacher;
	}
	@Override
	public ITeacherDao getDaoInterface() {
		return teacher;
	}
	public List<Teacher> getAllByDepartmentId(Integer id) {
		return teacher.findByDepartmentId(id);
	}
	public List<Teacher> getAllByInstituteId(Integer id) {
		return teacher.findByInstituteId(id);
	}
	public Teacher getAllByUserId(Integer id) {
		return teacher.findByUserId(id);
	}
	public Teacher getAllByName(String firstName, String lastName) {
		return teacher.findByName(firstName, lastName);
	}
	public void updatePhoto(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherPhoto(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateTitle(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherTitle(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFirstname(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherFirstname(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateSecondname(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherSecondname(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateLastname(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherLastname(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateBirthdate(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherBirthdate(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updatePhone(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherPhone(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateRoom(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherRoom(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateWebsite(Integer teacherId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setTeacherWebsite(newValue);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateAddress(Integer teacherId, Integer addressId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setAddressId(addressId);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateDepartment(Integer teacherId, Integer departmentId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setDepartmentId(departmentId);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateInstitute(Integer teacherId, Integer instituteId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Teacher entity = null;
		
		try {
			criteria = session.createCriteria(Teacher.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			entity = (Teacher)criteria.list().get(0);
			
			entity.setInstituteId(instituteId);
			
			teacher.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public Certificate getByTeacherId(Integer id) {
		return new CertificateService().getByTeacherId(id);
	}
	public List<Course> getAllCoursesByTeacherId(Integer id) {
		return new TeachersCoursesService().getAllByTeacherId(id);
	}
	public List<Course> getAllCourseFormByTeacherId(Integer id) {
		return new TeachersCoursesService().getAllByTeacherId(id);
	}
}