package app.services;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.humanity.details.Address;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.user.User;
import model.dao.interfaces.ITeacherDao;
import model.entity.Entity;
@Service
public class TeacherService extends DaoService<ITeacherDao> {
	public TeacherService() {
		super(DaoFactory.Dao.TEACHER);
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
		
		teacherEntity.setTeacherRoom(teacher.getDetails().getRoom());
		teacherEntity.setTeacherWebsite(teacher.getDetails().getWebsite());
		teacherEntity.setTeacherTitle(teacher.getDetails().getTitle().getName());
		
		createInstituteDepartment(teacher, teacherEntity);	
		createAddress(teacher, teacherEntity);
	}


	@Override
	protected void createFromEntity(Entity entity, Object base) {
		// TODO Auto-generated method stub
		
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