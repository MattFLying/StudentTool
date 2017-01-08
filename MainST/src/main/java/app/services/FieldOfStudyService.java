package app.services;

import java.util.ArrayList;
import java.util.List;
import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import model.dao.interfaces.IFieldOfStudyDao;
import model.entity.Entity;

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
		
		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByName(field.getDetails().getDepartment().getDetails().getDepartmentShortName());

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
	public List<FieldOfStudy> findAll() {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		dao().findAll(model.entity.FieldOfStudy.class).forEach( (x) -> {
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
}