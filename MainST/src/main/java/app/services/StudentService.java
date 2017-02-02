package app.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.excel.StudentsExcel;
import core.humanity.details.Address;
import core.humanity.student.IStudent;
import core.humanity.student.Student;
import core.study.course.Course;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.grade.Grade;
import core.study.group.Group;
import core.user.User;
import model.dao.interfaces.IStudentDao;
import model.entity.Entity;

/***
 * Class as service represents Student. In this class contains all possible
 * operations with student and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class StudentService extends DaoService<IStudentDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public StudentService() {
		super(DaoFactory.Dao.STUDENT);
	}

	@Override
	public IStudentDao getDao() {
		return (IStudentDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Student studentEntity = (model.entity.Student) entity;
		model.entity.StudentId studentId = new model.entity.StudentId();
		studentEntity.setId(studentId);
		IStudent student = (Student) base;

		studentEntity.setStudentFirstname(student.getDetails().getFirstName());
		studentEntity.setStudentSecondname(student.getDetails().getSecondName());
		studentEntity.setStudentLastname(student.getDetails().getLastName());
		studentEntity.setStudentPesel(student.getDetails().getPesel());
		studentEntity.setStudentBirthdate(student.getDetails().getBirthDate());
		studentEntity.setStudentPhone(student.getDetails().getPhoneNumber());
		studentEntity.setStudentBankNumber(student.getDetails().getBankNumber());
		studentEntity.setStudentEmail(student.getDetails().getEmail());

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

	/***
	 * Method to create student title.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createStudentTitle(IStudent student, model.entity.Student studentEntity) {
		if (student.getDetails().getTitle() == null) {
			studentEntity.setStudentTitle(student.getDetails().getTitle().NONE.getName());
		} else {
			studentEntity.setStudentTitle(student.getDetails().getTitle().getName());
		}
	}

	/***
	 * Method to create student start and end date of study.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createStartEndDate(IStudent student, model.entity.Student studentEntity) {
		if (student.getDetails().getStartStudyDate() == null) {
			studentEntity.setStudentStartDate(null);
		} else {
			studentEntity.setStudentStartDate(student.getDetails().getStartStudyDate().toString());
		}
		if (student.getDetails().getEndStudyDate() == null) {
			studentEntity.setStudentEndDate(null);
		} else {
			studentEntity.setStudentEndDate(student.getDetails().getEndStudyDate().toString());
		}
	}

	/***
	 * Method to create student form of study.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createFormOfStudy(IStudent student, model.entity.Student studentEntity) {
		if (student.getDetails().getFormOfStudy() == null) {
			studentEntity.setStudentFormOfStudy(student.getDetails().getFormOfStudy().NONE.getName());
		} else {
			studentEntity.setStudentFormOfStudy(student.getDetails().getFormOfStudy().getName());
		}
	}

	/***
	 * Method to create student study system.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createStudySystem(IStudent student, model.entity.Student studentEntity) {
		if (student.getDetails().getStudySystem() == null) {
			studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().NONE.getName());
		} else {
			studentEntity.setStudentStudySystem(student.getDetails().getStudySystem().getName());
		}
	}

	/***
	 * Method to create student group.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createGroup(IStudent student, model.entity.Student studentEntity) {
		if (!student.getDetails().getGroup().getDetails().getGroupName().replaceAll(",", "").equals("")) {
			model.entity.Group group = new GroupService().getDao()
					.findByName(student.getDetails().getGroup().getDetails().getGroupName().replaceAll(",", ""));
			studentEntity.setGroupId(group.getGroupId());
		}
	}

	/***
	 * Method to create student specialization.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createSpecialization(IStudent student, model.entity.Student studentEntity) {
		if (student.getDetails().getSpecialization().getDetails().getSpecializationName() == null) {
			studentEntity.setSpecializationId(null);
		} else if (!student.getDetails().getSpecialization().getDetails().getSpecializationName().equals("")) {
			model.entity.Specialization spec = new SpecializationService().getDao()
					.findByName(student.getDetails().getSpecialization().getDetails().getSpecializationName());
			studentEntity.setSpecializationId(spec.getId().getSpecializationId());
		}
	}

	/***
	 * Method to create student field of study.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createFieldOfStudy(IStudent student, model.entity.Student studentEntity) {
		if (student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName() == null) {
			studentEntity.setFieldOfStudyId(null);
		} else if (!student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName().equals("")) {
			model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(
					student.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
			studentEntity.setFieldOfStudyId(field.getFieldOfStudyId());
		}
	}

	/***
	 * Method to create student address.
	 * 
	 * @param student
	 *            - student object
	 * @param studentEntity
	 *            - student entity
	 */
	private void createAddress(IStudent student, model.entity.Student studentEntity) {
		Address address = student.getDetails().getAddress();

		if (address.getCity() == null && address.getPostalCode() == null & address.getStreetFullAddress() == null) {
			studentEntity.setAddressId(null);
		} else if (!address.equals(null) && (!address.getCity().equals("") || !address.getPostalCode().equals("")
				|| !address.getStreetFullAddress().equals(""))) {
			address.setCity(student.getDetails().getAddress().getCity());
			address.setPostalCode(student.getDetails().getAddress().getPostalCode());
			address.setStreetFullAddress(student.getDetails().getAddress().getStreetFullAddress());
			AddressService as = new AddressService();
			studentEntity.setAddressId(as.saveWithId(address));
		}
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Student studentEntity = (model.entity.Student) entity;
		IStudent student = (Student) base;

		student.getDetails().setFirstName(studentEntity.getStudentFirstname());
		student.getDetails().setSecondName(studentEntity.getStudentSecondname());
		student.getDetails().setLastName(studentEntity.getStudentLastname());
		student.getDetails().setPesel(studentEntity.getStudentPesel());
		student.getDetails().setBirthDate(studentEntity.getStudentBirthdate());
		student.getDetails().setPhoneNumber(studentEntity.getStudentPhone());
		student.getDetails().setBankNumber(studentEntity.getStudentBankNumber());
		student.getDetails().setEmail(studentEntity.getStudentEmail());

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
		Department dept = new DepartmentService()
				.findDepartmentNameById(field.getDetails().getDepartment().getDetails().getId());

		student.getDetails().setAddress(address);
		student.getDetails().setGroup(group);
		student.getDetails().setFieldOfStudy(field);
		student.getDetails().setSpecialization(spec);
		student.getDetails().setDepartment(dept);
	}

	/***
	 * Method to create student from entity.
	 * 
	 * @param base
	 *            - base student object
	 * @param entity
	 *            - student entity
	 * @return student object
	 */
	private Student createFromEntity(Student base, model.entity.Student entity) {
		IStudent student = new Student();

		createFromEntity(entity, student);

		return student.getModel();
	}

	/***
	 * Method to search student by identificator.
	 * 
	 * @param id
	 *            - identificator of student
	 * @return student object
	 */
	public Student findById(Integer id) {
		IStudent student = new Student();

		createFromEntity(dao().findById(id), student);

		return student.getModel();
	}

	/***
	 * Method to create student by album number.
	 * 
	 * @param album
	 *            - album number of student
	 * @return student object
	 */
	public Student createStudent(String album) {
		IStudent student = new Student();
		model.entity.Student entity = new StudentService().getDao().findByAlbum(album);

		createFromEntity(entity, student);

		return student.getModel();
	}

	/***
	 * Method to find all courses of specific term number for student.
	 * 
	 * @param term
	 *            - term number
	 * @param field
	 *            - field of study name
	 * @return list of all courses for student on specific term
	 */
	public List<Course> findCoursesForStudentTerm(Integer term, String field) {
		List<Course> list = new ArrayList<Course>();

		list = new CourseService().findByTermAndFieldOfStudy(term, field);

		return list;
	}

	/***
	 * Method to find all grades for student from specific course.
	 * 
	 * @param course
	 *            - course identificator
	 * @param student
	 *            - student identificator
	 * @return list of all grades for student by specific course
	 */
	public List<Grade> findGradesForStudent(Integer course, Integer student) {
		List<Grade> list = new ArrayList<Grade>();

		return list;
	}

	/***
	 * Method to finnd all students by group identificator.
	 * 
	 * @param groupId
	 *            - group identificator
	 * @return list of all students from group
	 */
	public List<Student> findAllByGroupId(Integer groupId) {
		List<Student> list = new ArrayList<Student>();

		dao().findByGroupId(groupId).forEach((x) -> {
			list.add(createFromEntity(new Student(), x));
		});

		return list;
	}

	/***
	 * Method to create students from excel file.
	 * 
	 * @param inputStream
	 *            - input stream with excel file
	 * @return list of converted students from excel file
	 */
	private List<Student> getStudentsFromExcel(InputStream inputStream) {
		try {
			return new StudentsExcel().readStudentsFromExcel(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * Method to build and create students list from excel file.
	 * 
	 * @param inputStream
	 *            - input stream with excel file
	 * @throws Exception
	 */
	public void createStudentsFromExcel(InputStream inputStream) throws Exception {
		List<Student> list = getStudentsFromExcel(inputStream);

		for (Student student : list) {
			User user = new User();
			user.setPassword(student.getDetails().getFirstName() + student.getDetails().getAlbumNumber()
					+ student.getDetails().getLastName());

			save(student, user, 0);
		}
	}

	/***
	 * Method to save student in database.
	 * 
	 * @param student
	 *            - student object
	 * @param user
	 *            - user object
	 * @param enabled
	 *            - if student account should be enabled
	 * @throws Exception
	 */
	public void save(IStudent student, User user, Integer enabled) throws Exception {
		UsersService userService = new UsersService();

		user.setRole(User.Role.STUDENT);
		user.setLogin(student.getDetails().getAlbumNumber().toString());
		userService.createBasicUser(user, enabled);

		model.entity.Student entity = new model.entity.Student();
		createEntity(student, entity);

		entity.getId().setUserLogin(student.getDetails().getAlbumNumber().toString());

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update student in database.
	 * 
	 * @param student
	 *            - student object
	 * @param albumTemp
	 *            - album number saved in cache
	 * @throws Exception
	 */
	public void updateStudent(IStudent student, String albumTemp) throws Exception {
		UsersService userService = new UsersService();
		model.entity.Student entity = new model.entity.Student();

		createEntity(student, entity);
		entity.getId().setStudentId(student.getDetails().getId());

		userService.updateUserLogin(albumTemp, student.getDetails().getAlbumNumber().toString());
		entity.getId().setUserLogin(student.getDetails().getAlbumNumber().toString());

		int success = dao().update(entity);
		if (success == 0) {
			throw new Exception();
		}
	}
}