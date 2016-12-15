package model.dao.interfaces;

import java.util.List;
import model.entity.Student;
import model.entity.StudentId;

public interface IStudentDao extends IGenericDao<Student, StudentId> {
	List<Student> findByGroupId(Integer id);
	List<Student> findByFieldOfStudyId(Integer id);
	List<Student> findBySpecializationId(Integer id);
	Student findByAlbum(String album);
	Student findByName(String firstName, String lastName);
	List<Student> findByGroupName(String name);
	List<Student> findByFieldOfStudyName(String name);
	List<Student> findBySpecializationName(String name);
	Student findByUserId(Integer id);
}