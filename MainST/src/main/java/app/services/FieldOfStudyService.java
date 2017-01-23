package app.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.JDBCException;
import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.group.Group;
import model.dao.FieldOfStudyDao;
import model.dao.GroupDao;
import model.dao.interfaces.IFieldOfStudyDao;
import model.entity.Entity;
@Service
public class FieldOfStudyService extends DaoService<IFieldOfStudyDao> {
	public FieldOfStudyService() {
		super(DaoFactory.Dao.FIELD_OF_STUDY);
	}
	
	
	@Override
	public IFieldOfStudyDao getDao() {
		return (IFieldOfStudyDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.FieldOfStudy fieldEntity = (model.entity.FieldOfStudy)entity;
		FieldOfStudy field = (FieldOfStudy)base;
		
		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByFullName(field.getDetails().getDepartment().getDetails().getDepartmentFullName());

		fieldEntity.setFieldOfStudyName(field.getDetails().getFieldOfStudyName());
		fieldEntity.setDepartmentId(dept.getDepartmentId());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.FieldOfStudy fieldEntity = (model.entity.FieldOfStudy)entity;
		FieldOfStudy field = (FieldOfStudy)base;
		
		Department dept = new DepartmentService().findDepartmentNameById(fieldEntity.getDepartmentId());	
		
		field.getDetails().setFieldOfStudyName(fieldEntity.getFieldOfStudyName());
		field.getDetails().setDepartment(dept);
	}
	private FieldOfStudy createFromEntity(FieldOfStudy base, model.entity.FieldOfStudy entity) {
		FieldOfStudy field = new FieldOfStudy();
		
		createFromEntity(entity, field);
		
		return field;
	}
	public FieldOfStudy findOneByFieldId(int id) {
		model.entity.FieldOfStudy entity = dao().findById(id);
		
		FieldOfStudy field = new FieldOfStudy();
		createFromEntity(entity, field);
		
		return field;
	}
	public FieldOfStudy findFieldOfStudyNameById(int id) {
		model.entity.FieldOfStudy entity = dao().findFieldOfStudyNameById(id);
		
		FieldOfStudy field = new FieldOfStudy();
		field.getDetails().setFieldOfStudyName(entity.getFieldOfStudyName());
		field.getDetails().setId(entity.getFieldOfStudyId());
		
		return field;
	}
	public FieldOfStudy findOneByName(String name) {
		model.entity.FieldOfStudy entity = dao().findByName(name);
		
		FieldOfStudy field = new FieldOfStudy();
		createFromEntity(entity, field);
		
		return field;
	}
	public List<FieldOfStudy> findAllByDepartmentFullName(String name) {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		dao().findByDepartmentFullName(name).forEach( (x) -> {
			list.add(createFromEntity(new FieldOfStudy(), x));
		});
		
		return list;
	}
	public List<FieldOfStudy> findAllByDepartmentShortName(String name) {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		dao().findByDepartmentName(name).forEach( (x) -> {
			list.add(createFromEntity(new FieldOfStudy(), x));
		});
		
		return list;
	}
	public void save(FieldOfStudy field) {
		model.entity.FieldOfStudy entity = new model.entity.FieldOfStudy();	
		createEntity(field, entity);
		
		dao().save(entity);
	}
	public void update(FieldOfStudy field) {
		model.entity.FieldOfStudy entity = new model.entity.FieldOfStudy();	
		createEntity(field, entity);
		
		dao().update(entity);
	}
	public void delete(FieldOfStudy field) {
		model.entity.FieldOfStudy entity = new model.entity.FieldOfStudy();	
		createEntity(field, entity);
		
		dao().delete(entity);
	}
	
	
	
	
	
	

	public List<FieldOfStudy> findAll() {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		dao().findAll(model.entity.FieldOfStudy.class).forEach( (x) -> {
			FieldOfStudy field = new FieldOfStudy();
			Department dept = new Department();
			
			field.getDetails().setFieldOfStudyName(x.getFieldOfStudyName());
			dept.getDetails().setId(x.getDepartmentId());
			field.getDetails().setDepartment(dept);
			
			list.add(field);
		});
		
		return list;
	}
	
	
	/*private static FieldOfStudyDao f = new FieldOfStudyDao();
	public static void main(String[] args) {
		
		HashMap<Department, List<FieldOfStudy>> list = findAllFieldsForAllDepartments();

		for (Map.Entry<Department, List<FieldOfStudy>> entry : list.entrySet()) {
			Department d = entry.getKey();
			List<FieldOfStudy> f = entry.getValue();
			
			System.out.println(d.getDetails().getDepartmentFullName() + ": " + f.size() + " " + f.get(0).getDetails().getFieldOfStudyName());
		}


		
	}*/
	
	
	
	public HashMap<Department, List<FieldOfStudy>> findAllFieldsForAllDepartments() {
		HashMap<model.entity.Department, List<model.entity.FieldOfStudy>> entities = dao().findAllFieldsForAllDepartments();
		HashMap<Department, List<FieldOfStudy>> list = new HashMap<Department, List<FieldOfStudy>>();
		
		for(Map.Entry<model.entity.Department, List<model.entity.FieldOfStudy>> entry : entities.entrySet()) {
			Department dept = new Department();
			dept.getDetails().setDepartmentFullName(entry.getKey().getDepartmentDescription());
			dept.getDetails().setId(entry.getKey().getDepartmentId());
			
			List<FieldOfStudy> fields = new ArrayList<FieldOfStudy>();
			for(model.entity.FieldOfStudy f : entry.getValue()) {
				FieldOfStudy field = new FieldOfStudy();
				field.getDetails().setFieldOfStudyName(f.getFieldOfStudyName());
				
				field.getDetails().setDepartment(dept);
				
				fields.add(field);
			}
			
			list.put(dept, fields);
		}
		
		return list;
	}
	
	
	
	
	private Department createDepartment(model.entity.Department entity) {
		Department department = new Department();
		
		department.getDetails().setDepartmentBuilding(entity.getDepartmentBuilding());
		department.getDetails().setDepartmentFullName(entity.getDepartmentDescription());
		department.getDetails().setDepartmentShortName(entity.getDepartmentName());
		department.getDetails().setId(entity.getDepartmentId());
		
		return department;
	}
	private FieldOfStudy createFieldOfStudy(model.entity.FieldOfStudy entity, Department dept) {
		FieldOfStudy field = new FieldOfStudy();
		
		field.getDetails().setFieldOfStudyName(entity.getFieldOfStudyName());
		field.getDetails().setId(entity.getFieldOfStudyId());
		field.getDetails().setDepartment(dept);
		
		return field;
	}
	private Group createGroup(model.entity.Group entity) {
		Group group = new Group();
		
		group.getDetails().setGroupName(entity.getGroupName());
		group.getDetails().setDescription(entity.getGroupDescription());
		
		return group;
	}
	private Specialization createSpecialization(model.entity.Specialization entity) {
		Specialization specialization = new Specialization();
		
		specialization.getDetails().setSpecializationName(entity.getSpecializationName());
		specialization.getDetails().setId(entity.getId().getSpecializationId());
		
		return specialization;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}