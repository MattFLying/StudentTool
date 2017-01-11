package model.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import model.entity.Department;
import model.entity.FieldOfStudy;

public interface IFieldOfStudyDao extends IGenericDao<FieldOfStudy, Integer> {
	FieldOfStudy findByName(String name);
	FieldOfStudy findFieldOfStudyIdByName(String name);
	FieldOfStudy findFieldOfStudyNameById(Integer id);
	List<FieldOfStudy> findByDepartmentId(Integer id);
	List<FieldOfStudy> findByDepartmentName(String name);
	List<FieldOfStudy> findByDepartmentFullName(String name);
	List<FieldOfStudy> findAllFields();
	HashMap<Department, List<FieldOfStudy>> findAllFieldsForAllDepartments();
}