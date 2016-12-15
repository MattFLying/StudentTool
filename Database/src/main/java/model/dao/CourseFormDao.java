package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.ICourseFormDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Course;
import model.entity.CourseForm;
import model.entity.CourseFormId;

public class CourseFormDao extends GenericDao<CourseForm, CourseFormId> implements ICourseFormDao {
	private CourseForm courseFormEntity;
	
	
	public CourseFormDao() {
		this.courseFormEntity = new CourseForm();
	}
	
	
	public List<CourseForm> findAllByCourseName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<CourseForm> list = new ArrayList<CourseForm>();
		
		try {
			Course course = new CourseDao().findByName(name);
			
			criteria = session.createCriteria(CourseForm.class);
			criteria.add(Restrictions.eq("id.courseId", course.getCourseId()));
			
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	public List<CourseForm> findAllByCourseId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<CourseForm> list = new ArrayList<CourseForm>();
		
		try {
			criteria = session.createCriteria(CourseForm.class);
			criteria.add(Restrictions.eq("id.courseId", id));
			
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	public CourseForm findByCourseNameAndForm(String name, String courseForm) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		CourseForm entity = null;
		
		try {
			Course course = new CourseDao().findByName(name);
			
			criteria = session.createCriteria(CourseForm.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.courseId", course.getCourseId()), Restrictions.eq("courseForm", name)));		
			
			entity = (CourseForm)criteria.list().get(0);
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