package app.services;

import java.util.ArrayList;
import java.util.List;
import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.department.Institute;
import model.dao.interfaces.IInstituteDao;
import model.entity.Entity;

public class InstituteService extends DaoService<IInstituteDao> {
	public InstituteService() {
		super(DaoFactory.Dao.INSTITUTE);
	}
	
	
	@Override
	public IInstituteDao getDao() {
		return (IInstituteDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Institute instituteEntity = (model.entity.Institute)entity;
		Institute institute = (Institute)base;
		
		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByName(institute.getDetails().getDepartment().getDetails().getDepartmentShortName());
		
		instituteEntity.setInstituteDescription(institute.getDetails().getInstituteFullName());
		instituteEntity.setInstituteName(institute.getDetails().getInstituteShortName());
		instituteEntity.setDepartmentId(dept.getDepartmentId());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Institute instituteEntity = (model.entity.Institute)entity;
		Institute institute = (Institute)base;
		
		Department dept = new DepartmentService().findDepartmentNameById(instituteEntity.getDepartmentId());
		
		institute.getDetails().setInstituteFullName(instituteEntity.getInstituteDescription());
		institute.getDetails().setInstituteShortName(instituteEntity.getInstituteName());
		institute.getDetails().setDepartment(dept);
	}
	private Institute createFromEntity(Institute base, model.entity.Institute entity) {
		Institute institute = new Institute();
		
		createFromEntity(entity, institute);
		
		return institute;
	}
	public Institute findByShortName(String name) {
		model.entity.Institute entity = dao().findByName(name);
		
		Institute institute = new Institute();
		createFromEntity(entity, institute);
		
		return institute;
	}
	public Institute findByFullName(String name) {
		model.entity.Institute entity = dao().findByFullName(name);
		
		Institute institute = new Institute();
		createFromEntity(entity, institute);
		
		return institute;
	}
	public List<Institute> findAllByDepartmentId(int id) {
		List<Institute> list = new ArrayList<Institute>();
		
		dao().findByDepartmentId(id).forEach( (x) -> {
			list.add(createFromEntity(new Institute(), x));
		});
		
		return list;
	}
	public List<Institute> findAllByDepartmentShortName(String name) {
		List<Institute> list = new ArrayList<Institute>();
		
		dao().findByDepartmentName(name).forEach( (x) -> {
			list.add(createFromEntity(new Institute(), x));
		});
		
		return list;
	}
	public Institute findInstituteNameById(int id) {
		model.entity.Institute entity = dao().findInstituteNameById(id);
		
		Institute institute = new Institute();
		institute.getDetails().setInstituteShortName(entity.getInstituteName());
		
		return institute;
	}
	public Institute findInstituteIdByName(String name) {
		model.entity.Institute entity = dao().findInstituteIdByName(name);
		
		Institute institute = new Institute();
		institute.getDetails().setId(entity.getInstituteId());
		
		return institute;
	}
	public void save(Institute institute) {
		model.entity.Institute entity = new model.entity.Institute();	
		createEntity(institute, entity);
		
		dao().save(entity);
	}
	public void update(Institute institute) {
		model.entity.Institute entity = new model.entity.Institute();	
		createEntity(institute, entity);
		
		dao().update(entity);
	}
	public void delete(Institute institute) {
		model.entity.Institute entity = new model.entity.Institute();	
		createEntity(institute, entity);
		
		dao().delete(entity);
	}
}