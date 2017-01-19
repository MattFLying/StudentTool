package app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.humanity.details.BankAccount;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.fieldofstudy.FieldOfStudy;
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
	
	
	public List<Grade> findByStudentIdAndCourseId(Integer course, Integer student) {
		List<Grade> list = new ArrayList<Grade>();
		
		dao().findByStudentIdAndCourseId(student, course).forEach( (x) -> {
			list.add(createFromEntity(new Grade(), x));
		});
		
		return list;
	}
	private Grade createFromEntity(Grade base, model.entity.Grade entity) {
		Grade field = new Grade();
		
		createFromEntity(entity, field);
		
		return field;
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
	
	public Grade findGrade(Integer id) {
		Grade grade = createFromEntity(new Grade(), dao().findByGradeId(id));
		
		return grade;
	}
	
	
	public void save(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().save(entity);
	}
	public void update(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().update(entity);
	}
	public void delete(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().delete(entity);
	}
}