package controller.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.StudentDao;
import model.dao.interfaces.IStudentDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Grade;
import model.entity.Student;

public class StudentService extends GenericDaoService<StudentDao, IStudentDao> {
	private StudentDao student = null;
	
	
	public StudentService() {
		this.student = new StudentDao();
	}


	@Override
	public StudentDao getDao() {
		return student;
	}
	@Override
	public IStudentDao getDaoInterface() {
		return student;
	}
	public List<Student> getAllByGroupId(Integer id) {
		return student.findByGroupId(id);
	}
	public List<Student> getAllByFieldOfStudyId(Integer id) {
		return student.findByFieldOfStudyId(id);
	}
	public List<Student> getAllBySpecializationId(Integer id) {
		return student.findBySpecializationId(id);
	}
	public List<Student> getAllByGroupName(String name) {
		return student.findByGroupName(name);
	}
	public List<Student> getAllByFieldOfStudyName(String name) {
		return student.findByFieldOfStudyName(name);
	}
	public List<Student> getAllBySpecializationName(String name) {
		return student.findBySpecializationName(name);
	}
	public Student getAllByAlbum(String album) {
		return student.findByAlbum(album);
	}
	public Student getAllByName(String firstName, String lastName) {
		return student.findByName(firstName, lastName);
	}
	public Student getAllByUserId(Integer id) {
		return student.findByUserId(id);
	}
	public void updatePhoto(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentPhoto(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateTitle(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentTitle(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFirstname(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentFirstname(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateSecondname(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentSecondname(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateLastname(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentLastname(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateBirthdate(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentBirthdate(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updatePhone(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentPhone(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateAlbum(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentAlbum(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFormOfStudy(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentFormOfStudy(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateTermTitle(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentTermTitle(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateDiplomaTitle(Integer studentId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentDiplomaTitle(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateCurrentTerm(Integer studentId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setStudentCurrentTerm(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateGroup(Integer studentId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setGroupId(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateAddress(Integer studentId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setAddressId(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFieldOfStudy(Integer studentId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setFieldOfStudyId(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateSpecialization(Integer studentId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Student entity = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			entity = (Student)criteria.list().get(0);
			
			entity.setSpecializationId(newValue);
			
			student.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public List<Grade> getAllByStudentId(Integer studentId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.studentId", studentId));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Grade> getAllByStudentIdAndTeacherId(Integer studentId, Integer teacherId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.studentId", studentId), Restrictions.eq("teacherId", teacherId)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Grade> getAllByStudentIdAndCourseId(Integer studentId, Integer courseId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.studentId", studentId), Restrictions.eq("id.courseId", courseId)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Grade> getAllByStudentIdAndCourseFormId(Integer studentId, Integer courseFormId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.studentId", studentId), Restrictions.eq("id.courseFormId", courseFormId)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Grade> getAllByStudentIdAndType(Integer studentId, String gradeType) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.studentId", studentId), Restrictions.eq("gradeType", gradeType)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}