package app.services;

import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.humanity.details.Address;
import core.humanity.teacher.ITeacher;
import core.humanity.teacher.Teacher;
import core.security.certificate.Certificate;
import core.study.department.Department;
import core.study.department.Institute;
import core.user.User;
import model.dao.TeachersCoursesDao;
import model.dao.interfaces.ITeacherDao;
import model.entity.Entity;

/***
 * Class as service represents Teacher. In this class contains all possible
 * operations with teacher and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class TeacherService extends DaoService<ITeacherDao> {
	private static final int CERTIFICATE_VALIDITY_YEARS = 5;
	private TeachersCoursesDao teachersCoursesDao;

	/***
	 * Default constructor sets basic fields.
	 */
	public TeacherService() {
		super(DaoFactory.Dao.TEACHER);
		this.teachersCoursesDao = new TeachersCoursesDao();
	}

	@Override
	public ITeacherDao getDao() {
		return (ITeacherDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Teacher teacherEntity = (model.entity.Teacher) entity;
		model.entity.TeacherId teacherId = new model.entity.TeacherId();
		teacherEntity.setId(teacherId);
		ITeacher teacher = (Teacher) base;

		teacherEntity.setTeacherFirstname(teacher.getDetails().getFirstName());
		teacherEntity.setTeacherSecondname(teacher.getDetails().getSecondName());
		teacherEntity.setTeacherLastname(teacher.getDetails().getLastName());
		teacherEntity.setTeacherBirthdate(teacher.getDetails().getBirthDate());
		teacherEntity.setTeacherPhone(teacher.getDetails().getPhoneNumber());

		teacherEntity.setTeacherRoom(teacher.getDetails().getRoom());
		teacherEntity.setTeacherWebsite(teacher.getDetails().getWebsite());
		teacherEntity.setTeacherTitle(teacher.getDetails().getTitle().getName());

		createInstituteDepartment(teacher, teacherEntity);
		createAddress(teacher, teacherEntity);
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Teacher teacherEntity = (model.entity.Teacher) entity;
		ITeacher teacher = (Teacher) base;

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

	/***
	 * Method to search teacher by identificator.
	 * 
	 * @param id
	 *            - teacher identificator
	 * @return teacher object
	 */
	public Teacher findById(Integer id) {
		ITeacher teacher = new Teacher();

		createFromEntity(dao().findById(id), teacher);

		return teacher.getModel();
	}

	/***
	 * Method to create teacher by user login.
	 * 
	 * @param userLogin
	 *            - user login of teacher
	 * @return teacher object
	 */
	public Teacher createTeacher(String userLogin) {
		ITeacher teacher = new Teacher();
		model.entity.Teacher entity = new TeacherService().getDao().findByLogin(userLogin);

		createFromEntity(entity, teacher);

		return teacher.getModel();
	}

	/***
	 * Method to find teacher by first name and last name.
	 * 
	 * @param firstName
	 *            - first name of teacher
	 * @param lastName
	 *            - last name of teacher
	 * @return teacher object
	 */
	public Teacher findTeacher(String firstName, String lastName) {
		ITeacher teacher = new Teacher();
		model.entity.Teacher entity = dao().findByName(firstName, lastName);

		createFromEntity(entity, teacher);

		return teacher.getModel();
	}

	/***
	 * Method to update teacher in database.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @param login
	 *            - user login of teacher
	 * @throws Exception
	 */
	public void updateTeacher(ITeacher teacher, String login) throws Exception {
		UsersService userService = new UsersService();
		model.entity.Teacher teacherEntity = new model.entity.Teacher();
		model.entity.TeacherId teacherId = new model.entity.TeacherId();
		teacherEntity.setId(teacherId);

		createEntity(teacher, teacherEntity);
		teacherEntity.getId().setTeacherId(teacher.getDetails().getId());

		userService.updateUserLogin(createTeacherLogin(login), createTeacherLogin(teacher.getModel()));
		teacherEntity.getId().setUserLogin(createTeacherLogin(teacher.getModel()));

		int success = dao().update(teacherEntity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to create address for teacher.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @param teacherEntity
	 */
	private void createAddress(ITeacher teacher, model.entity.Teacher teacherEntity) {
		Address address = teacher.getDetails().getAddress();

		if (!address.equals(null) && (!address.getCity().equals("") || !address.getPostalCode().equals("")
				|| !address.getStreetFullAddress().equals(""))) {
			address.setCity(teacher.getDetails().getAddress().getCity());
			address.setPostalCode(teacher.getDetails().getAddress().getPostalCode());
			address.setStreetFullAddress(teacher.getDetails().getAddress().getStreetFullAddress());
			AddressService as = new AddressService();
			teacherEntity.setAddressId(as.saveWithId(address));
		}
	}

	/***
	 * Method to build department and institute for teacher.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @param teacherEntity
	 *            - teacher entity
	 */
	private void createInstituteDepartment(ITeacher teacher, model.entity.Teacher teacherEntity) {
		if (!teacher.getDetails().getInstitute().getDetails().getInstituteFullName().equals("")) {
			model.entity.Institute institute = new InstituteService().getDao()
					.findInstituteIdByName(teacher.getDetails().getInstitute().getDetails().getInstituteFullName());
			teacherEntity.setInstituteId(institute.getInstituteId());
			teacherEntity.setDepartmentId(institute.getDepartmentId());
		}
	}

	/***
	 * Method to save etacher in database.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @param user
	 *            - user object
	 * @param enabled
	 *            - if teacher account should be anbled
	 * @throws Exception
	 */
	public void save(ITeacher teacher, User user, Integer enabled) throws Exception {
		UsersService userService = new UsersService();

		user.setRole(User.Role.TEACHER);
		user.setLogin(createTeacherLogin(teacher.getModel()));
		userService.createBasicUser(user, enabled);

		model.entity.Teacher entity = new model.entity.Teacher();
		createEntity(teacher, entity);

		entity.getId().setUserLogin(user.getLogin());

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}

		Certificate certificate = new Certificate();
		certificate.generateCertificate(createTeacherCertificateOwner(teacher.getModel()),
				createTeacherCertificateInitials(teacher.getModel()), CERTIFICATE_VALIDITY_YEARS, user.getPassword());
	}

	/***
	 * Method to build teacher login.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @return converted from details teacher login
	 */
	private String createTeacherLogin(ITeacher teacher) {
		return String.format("%1$s.%2$s", teacher.getDetails().getFirstName().charAt(0),
				teacher.getDetails().getLastName().substring(0, 3));
	}

	/***
	 * Method to build teacher login.
	 * 
	 * @param teacher
	 *            - teacher name details as string
	 * @return converted from details teacher login
	 */
	private String createTeacherLogin(String teacher) {
		String[] names = teacher.split(" ");
		return String.format("%1$s.%2$s", names[0].charAt(0), names[1].substring(0, 3));
	}

	/***
	 * Method to create owner name of teacher for certificate.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @return created name of teacher owner for certificate
	 */
	private String createTeacherCertificateOwner(ITeacher teacher) {
		return String.format("%1$s %2$s %3$s", teacher.getDetails().getTitle().getName(),
				teacher.getDetails().getFirstName(), teacher.getDetails().getLastName());
	}

	/***
	 * Method to create initials for certificate from teacher name.
	 * 
	 * @param teacher
	 *            - teacher object
	 * @return initials of teacher
	 */
	private String createTeacherCertificateInitials(ITeacher teacher) {
		return String.format("%1$s.%2$s.", teacher.getDetails().getFirstName().charAt(0),
				teacher.getDetails().getLastName().charAt(0));
	}
}