package app.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.excel.StudentsExcel;
import core.humanity.details.Address;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.grade.Grade;
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
		
		createStudentTitle(student, studentEntity);
		createStartEndDate(student, studentEntity);
		createFormOfStudy(student, studentEntity);
		createStudySystem(student, studentEntity);
		createFieldOfStudy(student, studentEntity);
		createSpecialization(student, studentEntity);
		createGroup(student, studentEntity);		
		createAddress(student, studentEntity);
	}
	private void createStudentTitle(Student student, model.entity.Student studentEntity) {
		if(student.getDetails().getTitle() == null) {
			studentEntity.setStudentTitle(student.getDetails().getTitle().NONE.getName());
		} else {
			studentEntity.setStudentTitle(student.getDetails().getTitle().getName());
		}
	}
	private void createStartEndDate(Student student, model.entity.Student studentEntity) {
		if(student.getDetails().getStartStudyDate() == null) {
			studentEntity.setStudentStartDate(null);
		} else {
			studentEntity.setStudentStartDate(student.getDetails().getStartStudyDate().toString());
		}
		if(student.getDetails().getEndStudyDate() == null) {
			studentEntity.setStudentEndDate(null);
		} else {
			studentEntity.setStudentEndDate(student.getDetails().getEndStudyDate().toString());
		}
	}
	private void createFormOfStudy(Student student, model.entity.Student studentEntity) {
		if(student.getDetails().getStudySystem() == null) {
			studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().NONE.getName());
		} else {
			studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().getName());
		}
	}
	private void createStudySystem(Student student, model.entity.Student studentEntity) {
		if(student.getDetails().getStudySystem() == null) {
			studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().NONE.getName());
		} else {
			studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().getName());
		}
	}
	private void createGroup(Student student, model.entity.Student studentEntity) {
		if(!student.getDetails().getGroup().getDetails().getGroupName().replaceAll(",", "").equals("")) {
			model.entity.Group group = new GroupService().getDao().findByName(student.getDetails().getGroup().getDetails().getGroupName().replaceAll(",", ""));
			studentEntity.setGroupId(group.getGroupId());
		}
	}
	private void createSpecialization(Student student, model.entity.Student studentEntity) {
		if(student.getDetails().getSpecialization().getDetails().getSpecializationName() == null) {
			studentEntity.setSpecializationId(null);
		} else if(!student.getDetails().getSpecialization().getDetails().getSpecializationName().equals("")) {
			model.entity.Specialization spec = new SpecializationService().getDao().findByName(student.getDetails().getSpecialization().getDetails().getSpecializationName());
			studentEntity.setSpecializationId(spec.getId().getSpecializationId());	
		}
	}
	private void createFieldOfStudy(Student student, model.entity.Student studentEntity) {
		if(student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName() == null) {
			studentEntity.setFieldOfStudyId(null);
		} else if(!student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName().equals("")) {
			model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
			studentEntity.setFieldOfStudyId(field.getFieldOfStudyId());
		} 
	}
	private void createAddress(Student student, model.entity.Student studentEntity) {
		Address address = student.getDetails().getAddress();
		
		if(address.getCity() == null && address.getPostalCode() == null & address.getStreetFullAddress() == null) {
			studentEntity.setAddressId(null);
		} else if(!address.equals(null) && (!address.getCity().equals("") || !address.getPostalCode().equals("") || !address.getStreetFullAddress().equals(""))) {
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
		
		
		student.getDetails().setFirstName(studentEntity.getStudentFirstname());
		student.getDetails().setSecondName(studentEntity.getStudentSecondname());
		student.getDetails().setLastName(studentEntity.getStudentLastname());
		student.getDetails().setPesel(studentEntity.getStudentPesel());
		student.getDetails().setBirthDate(studentEntity.getStudentBirthdate());
		student.getDetails().setPhoneNumber(studentEntity.getStudentPhone());
		student.getDetails().setBankNumber(studentEntity.getStudentBankNumber());
		
		student.getDetails().setAlbumNumber(studentEntity.getStudentAlbum());
		student.getDetails().setCurrentTermNumber(studentEntity.getStudentCurrentTerm());
		student.getDetails().setStartStudyDate(studentEntity.getStudentStartDate());
		student.getDetails().setEndStudyDate(studentEntity.getStudentEndDate());
		student.getDetails().setTitle(studentEntity.getStudentTitle());
		
		student.getDetails().setFormOfStudy(studentEntity.getStudentFormOfStudy());
		student.getDetails().setStudySystem(studentEntity.getStudentStudySystem());	
		student.getDetails().setId(studentEntity.getId().getStudentId());
		
		Address address = new AddressService().findOneById(studentEntity.getAddressId());
		Group group = new GroupService().findOneByGroupId(studentEntity.getGroupId());
		FieldOfStudy field = new FieldOfStudyService().findOneByFieldId(studentEntity.getFieldOfStudyId());
		Specialization spec = new SpecializationService().findOneById(studentEntity.getSpecializationId());
		Department dept = new DepartmentService().findDepartmentNameById(field.getDetails().getDepartment().getDetails().getId());
		
		student.getDetails().setAddress(address);
		student.getDetails().setGroup(group);
		student.getDetails().setFieldOfStudy(field);
		student.getDetails().setSpecialization(spec);
		student.getDetails().setDepartment(dept);
	}
	private Student createFromEntity(Student base, model.entity.Student entity) {
		Student student = new Student();
		
		createFromEntity(entity, student);
		
		return student;
	}
	public Student findById(Integer id) {
		Student student = new Student();
		
		createFromEntity(dao().findById(id), student);
		
		return student;
	}
	
	public Student createStudent(String album) {
		Student student = new Student();
		model.entity.Student entity = new StudentService().getDao().findByAlbum(album);
		
		createFromEntity(entity, student);
		
		return student;
	}
	
	
	public List<Course> findCoursesForStudentTerm(Integer term, String field) {
		List<Course> list = new ArrayList<Course>();
		
		list = new CourseService().findByTermAndFieldOfStudy(term, field);
		
		return list;
	}
	
	public List<Grade> findGradesForStudent(Integer course, Integer student) {
		List<Grade> list = new ArrayList<Grade>();
		
		
		
		return list;
	}
	
	public List<Student> findAllByGroupId(Integer groupId) {
		List<Student> list = new ArrayList<Student>();
		
		dao().findByGroupId(groupId).forEach((x) -> {
			list.add(createFromEntity(new Student(), x));
		});
		
		return list;
	}
	
	private List<Student> getStudentsFromExcel(InputStream inputStream) {
		try {
			return new StudentsExcel().readStudentsFromExcel(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public void createStudentsFromExcel(InputStream inputStream) {
		List<Student> list = getStudentsFromExcel(inputStream);
		
		for(Student student : list) {
			User user = new User();
			user.setPassword(student.getDetails().getFirstName() + student.getDetails().getAlbumNumber() + student.getDetails().getLastName());
			
			save(student, user);
		}
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