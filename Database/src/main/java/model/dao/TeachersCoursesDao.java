package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.ITeachersCoursesDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Course;
import model.entity.TeachersCourses;
import model.entity.TeachersCoursesId;

public class TeachersCoursesDao extends GenericDao<TeachersCourses, TeachersCoursesId> implements ITeachersCoursesDao {
	private TeachersCourses teachersCoursesEntity;
	
	
	public TeachersCoursesDao() {
		this.teachersCoursesEntity = null;
	}
	
	
	public List<Course> findByTeacherId(Integer teacherId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Course> list = new ArrayList<Course>();
		
		try {
			criteria = session.createCriteria(TeachersCourses.class);
			criteria.add(Restrictions.eq("id.teacherId", teacherId));
			
			List<TeachersCourses> courses = criteria.list();
			for(TeachersCourses course : courses) {
				list.add(new CourseDao().findById(course.getId().getCourseId()));
			}
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<TeachersCourses> findByCourseId(Integer courseId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<TeachersCourses> list = new ArrayList<TeachersCourses>();
		
		try {
			criteria = session.createCriteria(TeachersCourses.class);
			criteria.add(Restrictions.eq("id.courseId", courseId));
			
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<TeachersCourses> findByCourseIdAndTeacherId(Integer courseId, Integer teacherId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<TeachersCourses> list = new ArrayList<TeachersCourses>();
		
		try {
			criteria = session.createCriteria(TeachersCourses.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.courseId", courseId), Restrictions.eq("id.teacherId", teacherId)));
			
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}	
}