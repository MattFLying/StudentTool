package app.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.humanity.details.Address;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.department.Department;
import core.study.department.Institute;
import core.user.User;
import model.dao.TeachersCoursesDao;
import model.dao.interfaces.ITeacherDao;
import model.entity.Entity;
@Service
public class TeacherService extends DaoService<ITeacherDao> {
	private TeachersCoursesDao teachersCoursesDao;
	
	public TeacherService() {
		super(DaoFactory.Dao.TEACHER);
		
		this.teachersCoursesDao = new TeachersCoursesDao();
	}
	
	
	@Override
	public ITeacherDao getDao() {
		return (ITeacherDao)dao;
	}


	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Teacher teacherEntity = (model.entity.Teacher)entity;
		model.entity.TeacherId teacherId = new model.entity.TeacherId();
		teacherEntity.setId(teacherId);
		Teacher teacher = (Teacher)base;
		
		teacherEntity.setTeacherFirstname(teacher.getDetails().getFirstName());
		teacherEntity.setTeacherSecondname(teacher.getDetails().getSecondName());
		teacherEntity.setTeacherLastname(teacher.getDetails().getLastName());
		teacherEntity.setTeacherBirthdate(teacher.getDetails().getBirthDate());
		teacherEntity.setTeacherPhone(teacher.getDetails().getPhoneNumber());
		System.out.println(teacher.getDetails().getRoom() + " " + teacher.getDetails().getWebsite());
		teacherEntity.setTeacherRoom(teacher.getDetails().getRoom());
		teacherEntity.setTeacherWebsite(teacher.getDetails().getWebsite());
		teacherEntity.setTeacherTitle(teacher.getDetails().getTitle().getName());
		
		createInstituteDepartment(teacher, teacherEntity);	
		createAddress(teacher, teacherEntity);
	}


	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Teacher teacherEntity = (model.entity.Teacher)entity;
		Teacher teacher = (Teacher)base;
		
		teacher.getDetails().setId(teacherEntity.getId().getTeacherId());
		
		teacher.getDetails().setFirstName(teacherEntity.getTeacherFirstname());
		teacher.getDetails().setSecondName(teacherEntity.getTeacherSecondname());
		teacher.getDetails().setLastName(teacherEntity.getTeacherLastname());
		teacher.getDetails().setBirthDate(teacherEntity.getTeacherBirthdate());
		teacher.getDetails().setPhoneNumber(teacherEntity.getTeacherPhone());
		
		teacher.getDetails().setTitle(teacherEntity.getTeacherTitle());
		teacher.getDetails().setWebsite(teacherEntity.getTeacherWebsite());
		teacher.getDetails().setRoom(teacherEntity.getTeacherRoom());
		
		Address address = new AddressService().findOneById(teacherEntity.getAddressId());
		Department dept = new DepartmentService().findDepartmentNameById(teacherEntity.getDepartmentId());
		Institute institute = new InstituteService().findInstituteNameById(teacherEntity.getInstituteId());
		
		teacher.getDetails().setAddress(address);
		teacher.getDetails().setDepartment(dept);
		teacher.getDetails().setInstitute(institute);
	}
	
	public Teacher findById(Integer id) {
		Teacher teacher = new Teacher();
		
		createFromEntity(dao().findById(id), teacher);
		
		return teacher;
	}
	
	
	
	public Teacher createTeacher(String userLogin) {
		Teacher teacher = new Teacher();
		model.entity.Teacher entity = new TeacherService().getDao().findByLogin(userLogin);
		
		createFromEntity(entity, teacher);
		
		return teacher;
	}
	
	
	
	
	
	
	private void createAddress(Teacher teacher, model.entity.Teacher teacherEntity) {
		Address address = teacher.getDetails().getAddress();
		
		if(!address.equals(null) && (!address.getCity().equals("") || !address.getPostalCode().equals("") || !address.getStreetFullAddress().equals(""))) {
			address.setCity(teacher.getDetails().getAddress().getCity());
			address.setPostalCode(teacher.getDetails().getAddress().getPostalCode());
			address.setStreetFullAddress(teacher.getDetails().getAddress().getStreetFullAddress());
			AddressService as = new AddressService();
			teacherEntity.setAddressId(as.saveWithId(address));
		}
	}
	private void createInstituteDepartment(Teacher teacher, model.entity.Teacher teacherEntity) {
		if(!teacher.getDetails().getInstitute().getDetails().getInstituteFullName().equals("")) {
			model.entity.Institute institute = new InstituteService().getDao().findInstituteIdByName(teacher.getDetails().getInstitute().getDetails().getInstituteFullName());
			teacherEntity.setInstituteId(institute.getInstituteId());
			teacherEntity.setDepartmentId(institute.getDepartmentId());
		}
	}
	
	
	public void save(Teacher teacher, User user) {
		UsersService userService = new UsersService();
		
		user.setRole(User.Role.TEACHER);
		user.setLogin(createTeacherLogin(teacher));
		userService.createBasicUser(user);
		
		model.entity.Teacher entity = new model.entity.Teacher();	
		createEntity(teacher, entity);
		
		entity.getId().setUserLogin(user.getLogin());
		
		
		dao().save(entity);
	}
	
	
	private String createTeacherLogin(Teacher teacher) {
		return String.format("%1$s.%2$s", teacher.getDetails().getFirstName().charAt(0), teacher.getDetails().getLastName().substring(0, 3));
	}
	
	
	
	
}