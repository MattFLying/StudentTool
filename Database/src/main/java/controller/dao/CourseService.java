package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.CourseDao;
import model.dao.CourseFormDao;
import model.dao.interfaces.ICourseDao;
import model.dao.interfaces.ICourseFormDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Course;
import model.entity.CourseForm;

public class CourseService extends GenericDaoService<CourseDao, ICourseDao> {
	private CourseDao course = null;
	private CourseFormDao form = null;
	
	
	public CourseService() {
		this.course = new CourseDao();
		this.form = new CourseFormDao();
	}


	@Override
	public CourseDao getDao() {
		return course;
	}
	@Override
	public ICourseDao getDaoInterface() {
		return course;
	}
	public Course getByName(String name) {
		return course.findByName(name);
	}
	public List<Course> getAllByTermAndFieldOfStudyId(Integer term, Integer fieldOfStudyId) {
		return course.findByTermAndFieldOfStudyId(term, fieldOfStudyId);
	}
	public List<Course> getAllByTermAndFieldOfStudy(Integer term, String name) {
		return course.findByTermAndFieldOfStudy(term, name);
	}
	public void updateName(Integer courseId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Course entity = null;
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			entity = (Course)criteria.list().get(0);
			
			entity.setCourseName(newValue);
			
			course.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateEcts(Integer courseId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Course entity = null;
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			entity = (Course)criteria.list().get(0);
			
			entity.setCourseEcts(newValue);
			
			course.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateTerm(Integer courseId, Integer newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Course entity = null;
		
		try {
			criteria = session.createCriteria(Course.class);
			criteria.add(Restrictions.eq("courseId", courseId));
			entity = (Course)criteria.list().get(0);
			
			entity.setCourseTerm(newValue);
			
			course.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public CourseFormDao getFormDao() {
		return form;
	}
	public ICourseFormDao getFormDaoInterface() {
		return form;
	}
	public CourseForm getByCourseNameAndForm(String name, String courseForm) {
		return form.findByCourseNameAndForm(name, courseForm);
	}
}