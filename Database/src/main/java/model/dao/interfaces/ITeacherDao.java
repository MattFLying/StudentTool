package model.dao.interfaces;

import java.util.List;
import model.entity.Teacher;
import model.entity.TeacherId;

public interface ITeacherDao extends IGenericDao<Teacher, TeacherId> {
	List<Teacher> findByDepartmentId(Integer id);
	List<Teacher> findByInstituteId(Integer id);
	Teacher findByName(String firstName, String lastName);
	Teacher findByUserId(Integer id);
	Teacher findByLogin(String userLogin);
	Teacher findById(Integer id);
}