package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.grade.Grade;
import core.study.grade.IGrade;
import model.dao.interfaces.IGradeDao;
import model.entity.Entity;

/***
 * Class as service represents Grade. In this class contains all possible
 * operations with grade and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class GradeService extends DaoService<IGradeDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public GradeService() {
		super(DaoFactory.Dao.GRADE);
	}

	@Override
	public IGradeDao getDao() {
		return (IGradeDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Grade gradeEntity = (model.entity.Grade) entity;
		model.entity.GradeId gradeEntityId = new model.entity.GradeId();
		IGrade grade = (Grade) base;

		gradeEntity.setId(gradeEntityId);

		gradeEntity.setGradeValue(grade.getDetails().getValue());
		gradeEntity.setGradeDescription(grade.getDetails().getDescription());
		gradeEntity.setGradeType(grade.getDetails().getGradeType().getName());

		model.entity.Teacher teacher = new TeacherService().getDao().findByName(
				grade.getDetails().getTeacher().getDetails().getFirstName(),
				grade.getDetails().getTeacher().getDetails().getLastName());
		gradeEntity.setTeacherId(teacher.getId().getTeacherId());

		model.entity.Course course = new CourseService().getDao().findByNameAndForm(
				grade.getDetails().getCourse().getDetails().getCourseName(),
				grade.getDetails().getCourse().getDetails().getCourseForm().getName());
		gradeEntity.getId().setCourseId(course.getCourseId());

		model.entity.Student student = new StudentService().getDao()
				.findByAlbum(grade.getDetails().getStudent().getDetails().getAlbumNumber().toString());
		gradeEntity.getId().setStudentId(student.getId().getStudentId());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Grade gradeEntity = (model.entity.Grade) entity;
		IGrade grade = (Grade) base;

		grade.getDetails().setId(gradeEntity.getId().getGradeId());
		grade.getDetails().setValue(gradeEntity.getGradeValue());
		grade.getDetails().setDescription(gradeEntity.getGradeDescription());
		grade.getDetails().setGradeType(gradeEntity.getGradeType());

		Teacher teacher = new TeacherService().findById(gradeEntity.getTeacherId());
		Student student = new StudentService().findById(gradeEntity.getId().getStudentId());
		Course course = new CourseService().findOneById(gradeEntity.getId().getCourseId());

		grade.getDetails().setCourse(course);
		grade.getDetails().setTeacher(teacher);
		grade.getDetails().setStudent(student);
	}

	/***
	 * Method to build and save grade in database.
	 * 
	 * @param grade
	 *            - grade object
	 * @throws Exception
	 */
	public void createGrade(IGrade grade) throws Exception {
		model.entity.Grade gradeEntity = new model.entity.Grade();
		model.entity.GradeId gradeEntityId = new model.entity.GradeId();

		gradeEntity.setId(gradeEntityId);

		gradeEntity.setGradeValue(grade.getDetails().getValue());
		gradeEntity.setGradeDescription(grade.getDetails().getDescription());
		gradeEntity.setGradeType(grade.getDetails().getGradeType().getName());

		gradeEntity.setTeacherId(grade.getDetails().getTeacher().getDetails().getId());
		gradeEntity.getId().setCourseId(grade.getDetails().getCourse().getDetails().getId());
		gradeEntity.getId().setStudentId(grade.getDetails().getStudent().getDetails().getId());

		int success = dao().save(gradeEntity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update grade in database.
	 * 
	 * @param grade
	 *            - grade object
	 * @throws Exception
	 */
	public void updateGrade(IGrade grade) throws Exception {
		model.entity.Grade gradeEntity = new model.entity.Grade();
		model.entity.GradeId gradeEntityId = new model.entity.GradeId();

		gradeEntity.setId(gradeEntityId);

		gradeEntity.setGradeValue(grade.getDetails().getValue());
		gradeEntity.setGradeDescription(grade.getDetails().getDescription());
		gradeEntity.setGradeType(grade.getDetails().getGradeType().getName());

		gradeEntity.setTeacherId(grade.getDetails().getTeacher().getDetails().getId());
		gradeEntity.getId().setCourseId(grade.getDetails().getCourse().getDetails().getId());
		gradeEntity.getId().setStudentId(grade.getDetails().getStudent().getDetails().getId());
		gradeEntity.getId().setGradeId(grade.getDetails().getId());

		int success = dao().update(gradeEntity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to search all grades for student identificator of course
	 * identificator by grade type.
	 * 
	 * @param course
	 *            - course identificator
	 * @param student
	 *            - student identificator
	 * @param gradeType
	 *            - grade type
	 * @return list with all grades for student
	 */
	public List<Grade> findByStudentIdAndCourseIdAndGradeType(Integer course, Integer student, String gradeType) {
		List<Grade> list = new ArrayList<Grade>();

		dao().findByStudentIdAndCourseIdAndGradeType(student, course, gradeType).forEach((x) -> {
			list.add(createFromEntity(new Grade(), x));
		});

		return list;
	}

	/***
	 * Method to search all grades for student identificator of course
	 * identificator by grade type and teacher identificator.
	 * 
	 * @param course
	 *            - course identificator
	 * @param student
	 *            - student identificator
	 * @param teacher
	 *            - teacher identificator
	 * @param gradeType
	 *            - grade type
	 * @return list with all grades for student
	 */
	public List<Grade> findByStudentIdAndCourseIdAndTeacherIdAndGradeType(Integer course, Integer student,
			Integer teacher, String gradeType) {
		List<Grade> list = new ArrayList<Grade>();

		dao().findByStudentIdAndCourseIdAndTeacherIdAndGradeType(course, student, teacher, gradeType).forEach((x) -> {
			list.add(createFromEntity(new Grade(), x));
		});

		return list;
	}

	/***
	 * Method to create grade object from entity.
	 * 
	 * @param base
	 *            - base grade object
	 * @param entity
	 *            - grade entity
	 * @return grade object
	 */
	private Grade createFromEntity(Grade base, model.entity.Grade entity) {
		IGrade grade = new Grade();

		createFromEntity(entity, grade);

		return grade.getModel();
	}

	/***
	 * Method to find grade by identificator.
	 * 
	 * @param id
	 *            - grade identificator
	 * @return grade object
	 */
	public Grade findGrade(Integer id) {
		IGrade grade = createFromEntity(new Grade(), dao().findByGradeId(id));

		return grade.getModel();
	}
}