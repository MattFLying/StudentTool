package app.services.factory;

import model.dao.*;
import model.dao.interfaces.GenericDao;

public final class DaoFactory {
	public enum Dao {
		ADDRESS, BANK, CERTIFICATE, COURSE, DEPARTMENT, FIELD_OF_STUDY, GRADE, GROUP, INSTITUTE, SPECIALIZATION, STUDENT, TEACHER, TEACHERS_COURSES, USER;
	}
	
	public GenericDao<?, ?> get(Dao type) {
		switch(type) {
			case ADDRESS:
				return new AddressDao();
			case BANK:
				return new BankDao();
			case CERTIFICATE:
				return new CertificateDao();
			case COURSE:
				return new CourseDao();
			case DEPARTMENT:
				return new DepartmentDao();
			case FIELD_OF_STUDY:
				return new FieldOfStudyDao();
			case GRADE:
				return new GradeDao();
			case GROUP:
				return new GroupDao();
			case INSTITUTE:
				return new InstituteDao();
			case SPECIALIZATION:
				return new SpecializationDao();
			case STUDENT:
				return new StudentDao();
			case TEACHER:
				return new TeacherDao();
			case TEACHERS_COURSES:
				return new TeachersCoursesDao();
			case USER:
				return new UserDao();
			default:
				return null;
		}
	}
}