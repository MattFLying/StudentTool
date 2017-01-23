package app.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.study.course.Course;
import core.study.details.CourseForm;
import core.study.fieldofstudy.FieldOfStudy;
import model.dao.TeachersCoursesDao;
import model.dao.interfaces.ICourseDao;
import model.entity.Entity;
@Service
public class CourseService extends DaoService<ICourseDao> {
	public CourseService() {
		super(DaoFactory.Dao.COURSE);
	}
	
	
	@Override
	public ICourseDao getDao() {
		return (ICourseDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Course courseEntity = (model.entity.Course)entity;
		Course course = (Course)base;
		
		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(course.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
		
		courseEntity.setCourseName(course.getDetails().getCourseName());
		courseEntity.setCourseTerm(course.getDetails().getTerm());
		courseEntity.setFieldOfStudyId(field.getFieldOfStudyId());
		courseEntity.setCourseForm(course.getDetails().getCourseForm().getName());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Course courseEntity = (model.entity.Course)entity;
		Course course = (Course)base;
		
		FieldOfStudy field = new FieldOfStudyService().findFieldOfStudyNameById(courseEntity.getFieldOfStudyId());
		
		course.getDetails().setCourseForm(courseEntity.getCourseForm());
		course.getDetails().setCourseName(courseEntity.getCourseName());
		course.getDetails().setTerm(courseEntity.getCourseTerm());
		course.getDetails().setFieldOfStudy(field);
		course.getDetails().setId(courseEntity.getCourseId());
	}
	private Course createFromEntity(Course base, model.entity.Course entity) {
		Course course = new Course();
		
		createFromEntity(entity, course);
		
		return course;
	}
	public Course findOneById(int id) {
		model.entity.Course entity = dao().findById(id);
		
		Course course = new Course();
		createFromEntity(entity, course);
		
		return course;
	}
	public Course findByNameAndForm(String name, String form) {
		model.entity.Course entity = dao().findByNameAndForm(name, form);
		
		Course course = new Course();
		createFromEntity(entity, course);
		
		return course;
	}
	public List<Course> findAllFormOfCourse(String name) {
		List<Course> list = new ArrayList<Course>();
		
		dao().findByName(name).forEach( (x) -> {
			list.add(createFromEntity(new Course(), x));
		});
		
		return list;
	}
	
	
	
	public List<Course> findTeacherCoursesById(Integer teacherId){
		List<Course> list = new ArrayList<Course>();
		
		new TeachersCoursesDao().findByTeacherId(teacherId).forEach( (x) -> {
			list.add(createFromEntity(new Course(), x));
		});	
		
		return list;
	}
	
	
	
	
	
	public List<Course> findByTermAndFieldOfStudy(Integer term, String name) {
		List<Course> list = new ArrayList<Course>();
		
		dao().findByTermAndFieldOfStudy(term, name).forEach( (x) -> {
			list.add(createFromEntity(new Course(), x));
		});
		
		return list;
	}
	public List<Course> findByTermAndFieldOfStudyId(Integer term, Integer fieldId) {
		List<Course> list = new ArrayList<Course>();
		
		dao().findByTermAndFieldOfStudyId(term, fieldId).forEach( (x) -> {
			list.add(createFromEntity(new Course(), x));
		});
		
		return list;
	}
	public void save(Course course) {
		model.entity.Course entity = new model.entity.Course();	
		
		for(CourseForm form : course.getDetails().getCourseForms()) {
			course.getDetails().setCourseForm(form);
			
			createEntity(course, entity);
			dao().save(entity);
		}
	}
	public void update(Course course) {
		model.entity.Course entity = new model.entity.Course();	
		createEntity(course, entity);
		
		dao().update(entity);
	}
	public void delete(Course course) {
		model.entity.Course entity = new model.entity.Course();	
		createEntity(course, entity);
		
		dao().update(entity);
	}
}