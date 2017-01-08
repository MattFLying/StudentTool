package app.services;

import app.services.factory.DaoFactory;
import core.humanity.student.Student;
import core.study.group.Group;
import model.dao.interfaces.IStudentDao;
import model.entity.Entity;

public class StudentService extends DaoService<IStudentDao> {
	public StudentService() {
		super(DaoFactory.Dao.STUDENT);
		dao();
	}
	
	
	@Override
	public IStudentDao getDao() {
		return (IStudentDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Student studentEntity = (model.entity.Student)entity;
		Student student = (Student)base;
		
		studentEntity.setAddressId(null);
		studentEntity.setFieldOfStudyId(student.getDetails().getFieldOfStudy().getDetails().getId());
		studentEntity.setGroupId(student.getDetails().getGroup().getDetails().getId());
		studentEntity.setSpecializationId(student.getDetails().getSpecialization().getDetails().getId());
		studentEntity.setStudentAlbum(student.getDetails().getAlbumNumber());
		studentEntity.setStudentBirthdate(student.getDetails().getBirthDate().toString());
		studentEntity.setStudentCurrentTerm(student.getDetails().getCurrentTermNumber());
		studentEntity.setStudentDiplomaTitle(student.getDetails().getDiplomaTitle());
		studentEntity.setStudentEndDate(student.getDetails().getEndStudyDate().toString());
		studentEntity.setStudentFirstname(student.getDetails().getFirstName());
		studentEntity.setStudentFormOfStudy(student.getDetails().getFormOfStudy().toString());
		studentEntity.setStudentLastname(student.getDetails().getLastName());
		studentEntity.setStudentPesel(student.getDetails().getPesel());
		studentEntity.setStudentPhone(student.getDetails().getPhoneNumber());
		studentEntity.setStudentPhoto(null);
		studentEntity.setStudentSecondname(student.getDetails().getSecondName());
		studentEntity.setStudentStartDate(student.getDetails().getStartStudyDate().toString());
		studentEntity.setStudentTermTitle(student.getDetails().getTermTitle());
		studentEntity.setStudentTitle(student.getDetails().getTitle().toString());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Student studentEntity = (model.entity.Student)entity;
		Student student = (Student)base;
		
		
	}

	public void save(Student student) {
		model.entity.Student entity = new model.entity.Student();	
		createEntity(student, entity);
		
		dao().save(entity);
	}
	public void update(Student student) {
		model.entity.Student entity = new model.entity.Student();	
		createEntity(student, entity);
		
		dao().update(entity);
	}
	public void delete(Student student) {
		model.entity.Student entity = new model.entity.Student();	
		createEntity(student, entity);
		
		dao().delete(entity);
	}
}