package model.dao.interfaces;

import java.util.List;
import model.entity.FieldOfStudy;

public interface IFieldOfStudyDao extends IGenericDao<FieldOfStudy, Integer> {
	FieldOfStudy findByName(String name);
	List<FieldOfStudy> findByDepartmentId(Integer id);
	List<FieldOfStudy> findByDepartmentName(String name);
	List<FieldOfStudy> findByDepartmentFullName(String name);
}