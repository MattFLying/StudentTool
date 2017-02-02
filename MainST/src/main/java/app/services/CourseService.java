package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.course.Course;
import core.study.course.ICourse;
import core.study.details.CourseForm;
import core.study.fieldofstudy.IFieldOfStudy;
import model.dao.TeachersCoursesDao;
import model.dao.interfaces.ICourseDao;
import model.entity.Entity;

/***
 * Class as service represents Course. In this class contains all possible
 * operations with course and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class CourseService extends DaoService<ICourseDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public CourseService() {
		super(DaoFactory.Dao.COURSE);
	}

	@Override
	public ICourseDao getDao() {
		return (ICourseDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Course courseEntity = (model.entity.Course) entity;
		ICourse course = (ICourse) base;

		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao()
				.findFieldOfStudyIdByName(course.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());

		courseEntity.setCourseName(course.getDetails().getCourseName());
		courseEntity.setCourseTerm(course.getDetails().getTerm());
		courseEntity.setFieldOfStudyId(field.getFieldOfStudyId());
		courseEntity.setCourseForm(course.getDetails().getCourseForm().getName());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Course courseEntity = (model.entity.Course) entity;
		ICourse course = (ICourse) base;

		IFieldOfStudy field = new FieldOfStudyService().findFieldOfStudyNameById(courseEntity.getFieldOfStudyId());

		course.getDetails().setCourseForm(courseEntity.getCourseForm());
		course.getDetails().setCourseName(courseEntity.getCourseName());
		course.getDetails().setTerm(courseEntity.getCourseTerm());
		course.getDetails().setFieldOfStudy(field);
		course.getDetails().setId(courseEntity.getCourseId());
	}

	/***
	 * Method for creating and returning course object from entity.
	 * 
	 * @param base
	 *            - base course object
	 * @param entity
	 *            - course entity
	 * @return course object converted from entity
	 */
	private Course createFromEntity(Course base, model.entity.Course entity) {
		ICourse course = new Course();

		createFromEntity(entity, course);

		return course.getModel();
	}

	/***
	 * Method to search course by identificator.
	 * 
	 * @param id
	 *            - course identificator
	 * @return course
	 */
	public Course findOneById(int id) {
		model.entity.Course entity = dao().findById(id);

		ICourse course = new Course();
		createFromEntity(entity, course);

		return course.getModel();
	}

	/***
	 * Method to search course by specific name and form of course.
	 * 
	 * @param name
	 *            - name of course
	 * @param form
	 *            - form of course
	 * @return course
	 */
	public Course findByNameAndForm(String name, String form) {
		model.entity.Course entity = dao().findByNameAndForm(name, form);

		ICourse course = new Course();
		createFromEntity(entity, course);

		return course.getModel();
	}

	/***
	 * Method to search all forms of course.
	 * 
	 * @param name
	 *            - course name
	 * @return course
	 */
	public List<Course> findAllFormOfCourse(String name) {
		List<Course> list = new ArrayList<Course>();

		dao().findByName(name).forEach((x) -> {
			list.add(createFromEntity(new Course(), x));
		});

		return list;
	}

	/***
	 * Method to search all courses for teacher by teacher identificator.
	 * 
	 * @param teacherId
	 *            - teacher identificator
	 * @return - list of courses
	 */
	public List<Course> findTeacherCoursesById(Integer teacherId) {
		List<Course> list = new ArrayList<Course>();

		new TeachersCoursesDao().findByTeacherId(teacherId).forEach((x) -> {
			list.add(createFromEntity(new Course(), x));
		});

		return list;
	}

	/***
	 * Method to update course in database.
	 * 
	 * @param course
	 *            - course object to update
	 * @throws Exception
	 */
	public void updateCourse(Course course) throws Exception {
		model.entity.Course entity = new model.entity.Course();

		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao()
				.findFieldOfStudyIdByName(course.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());

		entity.setCourseName(course.getDetails().getCourseName());
		entity.setCourseTerm(course.getDetails().getTerm());
		entity.setFieldOfStudyId(field.getFieldOfStudyId());
		entity.setCourseForm(course.getDetails().getCourseForm().getName());
		entity.setCourseId(course.getDetails().getId());

		int success = dao().update(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to search all courses for term on specific field of study name.
	 * 
	 * @param term
	 *            - term of course
	 * @param name
	 *            - field of study name
	 * @return list of courses
	 */
	public List<Course> findByTermAndFieldOfStudy(Integer term, String name) {
		List<Course> list = new ArrayList<Course>();

		dao().findByTermAndFieldOfStudy(term, name).forEach((x) -> {
			list.add(createFromEntity(new Course(), x));
		});

		return list;
	}

	/***
	 * Method to search all courses for term on specific field of study
	 * identificator.
	 * 
	 * @param term
	 *            - term of course
	 * @param fieldId
	 *            - field of study identificator
	 * @return list of courses
	 */
	public List<Course> findByTermAndFieldOfStudyId(Integer term, Integer fieldId) {
		List<Course> list = new ArrayList<Course>();

		dao().findByTermAndFieldOfStudyId(term, fieldId).forEach((x) -> {
			list.add(createFromEntity(new Course(), x));
		});

		return list;
	}

	/***
	 * Method allows to save course in database, if you check a few forms for
	 * specific course then all forms will be added.
	 * 
	 * @param course
	 *            - course object
	 * @throws Exception
	 */
	public void save(ICourse course) throws Exception {
		model.entity.Course entity = new model.entity.Course();

		for (CourseForm form : course.getDetails().getCourseForms()) {
			course.getDetails().setCourseForm(form);

			createEntity(course, entity);

			int success = dao().save(entity);
			if (success == 0) {
				throw new Exception();
			}
		}
	}
}