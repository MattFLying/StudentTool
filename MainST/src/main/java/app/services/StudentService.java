package app.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.humanity.details.Address;
import core.humanity.student.Student;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.group.Group;
import core.user.User;
import model.dao.interfaces.IStudentDao;
import model.entity.Entity;
@Service
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
		model.entity.StudentId studentId = new model.entity.StudentId();
		studentEntity.setId(studentId);
		Student student = (Student)base;
		
		studentEntity.setStudentFirstname(student.getDetails().getFirstName());
		studentEntity.setStudentSecondname(student.getDetails().getSecondName());
		studentEntity.setStudentLastname(student.getDetails().getLastName());
		studentEntity.setStudentPesel(student.getDetails().getPesel());
		studentEntity.setStudentBirthdate(student.getDetails().getBirthDate());
		studentEntity.setStudentPhone(student.getDetails().getPhoneNumber());
		studentEntity.setStudentBankNumber(student.getDetails().getBankNumber());
		
		studentEntity.setStudentAlbum(student.getDetails().getAlbumNumber());
		studentEntity.setStudentCurrentTerm(student.getDetails().getCurrentTermNumber());
		studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().getName());
		studentEntity.setStudentFormOfStudy(student.getDetails().getFormOfStudy().getName());
		studentEntity.setStudentStartDate(student.getDetails().getStartStudyDate().toString());
		studentEntity.setStudentEndDate(student.getDetails().getEndStudyDate().toString());
		studentEntity.setStudentTitle(student.getDetails().getTitle().getName());
		
		createFieldOfStudy(student, studentEntity);
		createSpecialization(student, studentEntity);
		createGroup(student, studentEntity);		
		createAddress(student, studentEntity);
	}
	private void createGroup(Student student, model.entity.Student studentEntity) {
		if(!student.getDetails().getGroup().getDetails().getGroupName().replaceAll(",", "").equals("")) {
			model.entity.Group group = new GroupService().getDao().findByName(student.getDetails().getGroup().getDetails().getGroupName().replaceAll(",", ""));
			studentEntity.setGroupId(group.getGroupId());
		}
	}
	private void createSpecialization(Student student, model.entity.Student studentEntity) {
		if(!student.getDetails().getSpecialization().getDetails().getSpecializationName().equals("")) {
			model.entity.Specialization spec = new SpecializationService().getDao().findByName(student.getDetails().getSpecialization().getDetails().getSpecializationName());
			studentEntity.setSpecializationId(spec.getId().getSpecializationId());	
		}
	}
	private void createFieldOfStudy(Student student, model.entity.Student studentEntity) {
		if(!student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName().equals("")) {
			model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
			studentEntity.setFieldOfStudyId(field.getFieldOfStudyId());
		}
	}
	private void createAddress(Student student, model.entity.Student studentEntity) {
		Address address = student.getDetails().getAddress();
		
		if(!address.equals(null) && (!address.getCity().equals("") || !address.getPostalCode().equals("") || !address.getStreetFullAddress().equals(""))) {
			address.setCity(student.getDetails().getAddress().getCity());
			address.setPostalCode(student.getDetails().getAddress().getPostalCode());
			address.setStreetFullAddress(student.getDetails().getAddress().getStreetFullAddress());
			AddressService as = new AddressService();
			studentEntity.setAddressId(as.saveWithId(address));
		}
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Student studentEntity = (model.entity.Student)entity;
		Student student = (Student)base;
		
		
	}

	
	
	public void save(Student student, User user) {
		UsersService userService = new UsersService();
		
		user.setRole(User.Role.STUDENT);
		user.setLogin(student.getDetails().getAlbumNumber().toString());
		userService.createBasicUser(user);
		
		model.entity.Student entity = new model.entity.Student();	
		createEntity(student, entity);
		
		entity.getId().setUserLogin(student.getDetails().getAlbumNumber().toString());
		
		
		dao().save(entity);
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