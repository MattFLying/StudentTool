package model.dao.interfaces;

import model.entity.Department;

public interface IDepartmentDao extends IGenericDao<Department, Integer> {
	Department findByName(String name);
	Department findByFullName(String description);
}