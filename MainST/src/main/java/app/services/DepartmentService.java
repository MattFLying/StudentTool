package app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import model.dao.interfaces.IDepartmentDao;
import model.entity.Entity;
@Service
public class DepartmentService extends DaoService<IDepartmentDao> {
	public DepartmentService() {
		super(DaoFactory.Dao.DEPARTMENT);
	}
	
	
	@Override
	public IDepartmentDao getDao() {
		return (IDepartmentDao)dao;
	}
	
	public Department findDepartmentNameById(int id) {
		model.entity.Department entity = dao().findDepartmentNameById(id);
		
		Department department = new Department();
		createFromEntity(entity, department);
		
		return department;
	}

	public Department findDepartmentIdByFullName(String name) {
		model.entity.Department entity = dao().findDepartmentIdByFullName(name);
		
		Department department = new Department();
		createFromEntity(entity, department);
		
		return department;
	}
	public Department findOneByDepartmentId(int id) {
		model.entity.Department entity = dao().findById(id);
		
		Department department = new Department();
		createFromEntity(entity, department);
		
		return department;
	}
	public Department findOneByShortName(String name) {
		model.entity.Department entity = dao().findByName(name);
		
		Department department = new Department();
		createFromEntity(entity, department);
		
		return department;
	}
	public Department findOneByFullName(String name) {
		model.entity.Department entity = dao().findByFullName(name);
		
		Department department = new Department();
		createFromEntity(entity, department);
		
		return department;
	}
	public void save(Department department) {
		model.entity.Department entity = new model.entity.Department();	
		createEntity(department, entity);
		
		dao().save(entity);
	}
	public void update(Department department) {
		model.entity.Department entity = new model.entity.Department();	
		createEntity(department, entity);
		
		dao().update(entity);
	}
	public void delete(Department department) {
		model.entity.Department entity = new model.entity.Department();	
		createEntity(department, entity);
		
		dao().delete(entity);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Department departmentEntity = (model.entity.Department)entity;
		Department department = (Department)base;
		
		departmentEntity.setDepartmentBuilding(department.getDetails().getDepartmentBuilding());
		departmentEntity.setDepartmentDescription(department.getDetails().getDepartmentFullName());
		departmentEntity.setDepartmentName(department.getDetails().getDepartmentShortName());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Department departmentEntity = (model.entity.Department)entity;
		Department department = (Department)base;
		
		department.getDetails().setDepartmentBuilding(departmentEntity.getDepartmentBuilding());
		department.getDetails().setDepartmentFullName(departmentEntity.getDepartmentDescription());
		department.getDetails().setDepartmentShortName(departmentEntity.getDepartmentName());
		department.getDetails().setId(departmentEntity.getDepartmentId());
	}
	private Department createFromEntity(Department base, model.entity.Department entity) {
		Department field = new Department();
		
		createFromEntity(entity, field);
		
		return field;
	}
	public List<Department> findAll() {
		List<Department> list = new ArrayList<Department>();
		
		dao().findAll(model.entity.Department.class).forEach( (x) -> {
			list.add(createFromEntity(new Department(), x));
		});
		
		return list;
	}
	
	
	
	
	
}