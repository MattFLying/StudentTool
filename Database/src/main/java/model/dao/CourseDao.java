package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.ICourseDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Course;
import model.entity.FieldOfStudy;

public class CourseDao extends GenericDao<Course, Integer> implements ICourseDao {
	private Course courseEntity;
	
	
	public CourseDao() {
		this.courseEntity = new Course();
	}
	

	public Course findByNameAndForm(String name, String form) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.and(Restrictions.eq("courseName", name), Restrictions.eq("courseForm", form)));
			courseEntity = (Course)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(courseEntity != null) {
			return courseEntity;
		} else {
			return null;
		}
	}
	public List<Course> findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Course> list = new ArrayList<Course>();
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.eq("courseName", name));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	public List<Course> findByFieldOfStudyId(Integer fieldOfStudyId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Course> list = new ArrayList<Course>();
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", fieldOfStudyId));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Course> findByTermAndFieldOfStudyId(Integer term, Integer fieldOfStudyId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Course> list = new ArrayList<Course>();
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.and(Restrictions.eq("courseTerm", term), Restrictions.eq("fieldOfStudyId", fieldOfStudyId)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Course> findByTermAndFieldOfStudy(Integer term, String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Course> list = new ArrayList<Course>();
		
		try {
			FieldOfStudy field = new FieldOfStudyDao().findByName(name);
			
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.and(Restrictions.eq("courseTerm", term), Restrictions.eq("fieldOfStudyId", field.getFieldOfStudyId())));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}