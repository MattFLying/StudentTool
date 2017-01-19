package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IStudentDao;
import model.db.hib.util.HibernateUtil;
import model.entity.FieldOfStudy;
import model.entity.Group;
import model.entity.Specialization;
import model.entity.Student;
import model.entity.StudentId;

public class StudentDao extends GenericDao<Student, StudentId> implements IStudentDao {
	private Student studentEntity;
	
	
	public StudentDao() {
		this.studentEntity = null;
	}


	public List<Student> findByGroupId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Student> list = new ArrayList<Student>();
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("groupId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}
		
		return list;
	}
	public List<Student> findByFieldOfStudyId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Student> list = new ArrayList<Student>();
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}
		
		return list;
	}
	public List<Student> findBySpecializationId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Student> list = new ArrayList<Student>();
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("specializationId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}
		
		return list;
	}
	public Student findByAlbum(String album) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("studentAlbum", Long.valueOf(album)));
			studentEntity = (Student)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}

		if(studentEntity != null) {
			return studentEntity;
		} else {
			return null;
		}
	}
	public Student findByName(String firstName, String lastName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.and(Restrictions.eq("studentFirstname", firstName), Restrictions.eq("studentLastname", lastName)));
			studentEntity = (Student)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}

		if(studentEntity != null) {
			return studentEntity;
		} else {
			return null;
		}
	}
	public List<Student> findByGroupName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Student> list = new ArrayList<Student>();
		
		try {
			Group group = new GroupDao().findByName(name);
			
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("groupId", group.getGroupId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}
		
		return list;
	}
	public List<Student> findByFieldOfStudyName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Student> list = new ArrayList<Student>();
		
		try {
			FieldOfStudy field = new FieldOfStudyDao().findByName(name);
			
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", field.getFieldOfStudyId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}
		
		return list;
	}
	public List<Student> findBySpecializationName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Student> list = new ArrayList<Student>();
		
		try {
			Specialization specialization = new SpecializationDao().findByName(name);
			
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("specializationId", specialization.getId().getSpecializationId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}
		
		return list;
	}
	public Student findByUserId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.userId", id));
			studentEntity = (Student)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}

		if(studentEntity != null) {
			return studentEntity;
		} else {
			return null;
		}
	}
	public Student findById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("id.studentId", id));
			studentEntity = (Student)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.evict(studentEntity);
			session.close();
		}

		if(studentEntity != null) {
			return studentEntity;
		} else {
			return null;
		}
	}
}