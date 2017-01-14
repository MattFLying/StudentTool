package model.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import model.entity.Department;
import model.entity.Institute;

public interface IInstituteDao extends IGenericDao<Institute, Integer> {
	Institute findByName(String name);
	Institute findByFullName(String name);
	Institute findInstituteNameById(Integer id);
	Institute findInstituteIdByName(String name);
	List<Institute> findByDepartmentId(Integer id);
	List<Institute> findByDepartmentName(String name);
	HashMap<Department, List<Institute>> findAllInstitutesForAllDepartments();
}