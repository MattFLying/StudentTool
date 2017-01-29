package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.grade.Grade;
import model.dao.interfaces.IGradeDao;
import model.entity.Entity;

@Service
public class GradeService extends DaoService<IGradeDao> {
	public GradeService() {
		super(DaoFactory.Dao.GRADE);
	}
	
	
	@Override
	public IGradeDao getDao() {
		return (IGradeDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Grade gradeEntity = (model.entity.Grade)entity;
		model.entity.GradeId gradeEntityId = new model.entity.GradeId();
		Grade grade = (Grade)base;
		
		gradeEntity.setId(gradeEntityId);
		
		gradeEntity.setGradeValue(grade.getDetails().getValue());
		gradeEntity.setGradeDescription(grade.getDetails().getDescription());
		gradeEntity.setGradeType(grade.getDetails().getGradeType().getName());
		
		
		model.entity.Teacher teacher = new TeacherService().getDao().findByName(grade.getDetails().getTeacher().getDetails().getFirstName(), grade.getDetails().getTeacher().getDetails().getLastName());		
		gradeEntity.setTeacherId(teacher.getId().getTeacherId());
		
		model.entity.Course course = new CourseService().getDao().findByNameAndForm(grade.getDetails().getCourse().getDetails().getCourseName(), grade.getDetails().getCourse().getDetails().getCourseForm().getName());
		gradeEntity.getId().setCourseId(course.getCourseId());
		
		model.entity.Student student = new StudentService().getDao().findByAlbum(grade.getDetails().getStudent().getDetails().getAlbumNumber().toString());
		gradeEntity.getId().setStudentId(student.getId().getStudentId());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Grade gradeEntity = (model.entity.Grade)entity;
		Grade grade = (Grade)base;
		
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
	public void createGrade(Grade grade) throws Exception {
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
		if(success == 0) {
			throw new Exception();
		}
	}
	public void updateGrade(Grade grade) throws Exception {
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
		if(success == 0) {
			throw new Exception();
		}
	}

	public List<Grade> findByStudentIdAndCourseIdAndGradeType(Integer course, Integer student, String gradeType) {
		List<Grade> list = new ArrayList<Grade>();
		
		dao().findByStudentIdAndCourseIdAndGradeType(student, course, gradeType).forEach( (x) -> {
			list.add(createFromEntity(new Grade(), x));
		});
		
		return list;
	}
	public List<Grade> findByStudentIdAndCourseIdAndTeacherIdAndGradeType(Integer course, Integer student, Integer teacher, String gradeType) {
		List<Grade> list = new ArrayList<Grade>();
		
		dao().findByStudentIdAndCourseIdAndTeacherIdAndGradeType(course, student, teacher, gradeType).forEach( (x) -> {
			list.add(createFromEntity(new Grade(), x));
		});
		
		return list;
	}
	private Grade createFromEntity(Grade base, model.entity.Grade entity) {
		Grade field = new Grade();
		
		createFromEntity(entity, field);
		
		return field;
	}
	public Grade findGrade(Integer id) {
		Grade grade = createFromEntity(new Grade(), dao().findByGradeId(id));
		
		return grade;
	}
	public void save(Grade grade) throws Exception {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		int success = dao().save(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void update(Grade grade) throws Exception {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		int success = dao().update(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void delete(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().delete(entity);
	}
}