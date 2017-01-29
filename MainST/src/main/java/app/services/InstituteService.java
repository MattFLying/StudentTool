package app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.department.Institute;
import model.dao.interfaces.IInstituteDao;
import model.entity.Entity;

@Service
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
		
		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByFullName(institute.getDetails().getDepartment().getDetails().getDepartmentFullName());
		
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
		institute.getDetails().setId(instituteEntity.getInstituteId());
	}
	public Institute findByShortName(String name) {
		model.entity.Institute entity = dao().findByName(name);
		
		Institute institute = new Institute();
		createFromEntity(entity, institute);
		
		return institute;
	}
	public Institute findById(Integer id) {
		model.entity.Institute entity = dao().findById(id);
		
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
		institute.getDetails().setInstituteFullName(entity.getInstituteDescription());
		
		return institute;
	}
	public Institute findInstituteIdByName(String name) {
		model.entity.Institute entity = dao().findInstituteIdByName(name);
		
		Institute institute = new Institute();
		institute.getDetails().setId(entity.getInstituteId());
		
		return institute;
	}
	public void update(Institute institute) throws Exception {
		model.entity.Institute entity = new model.entity.Institute();	
		createEntity(institute, entity);
		
		int success = dao().update(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void delete(Institute institute) {
		model.entity.Institute entity = new model.entity.Institute();	
		createEntity(institute, entity);
		
		dao().delete(entity);
	}
	public HashMap<Department, List<Institute>> findAllInstitutesForAllDepartments() {
		HashMap<model.entity.Department, List<model.entity.Institute>> entities = dao().findAllInstitutesForAllDepartments();
		HashMap<Department, List<Institute>> list = new HashMap<Department, List<Institute>>();
		
		for(Map.Entry<model.entity.Department, List<model.entity.Institute>> entry : entities.entrySet()) {
			Department dept = new Department();
			dept.getDetails().setDepartmentFullName(entry.getKey().getDepartmentDescription());
			dept.getDetails().setId(entry.getKey().getDepartmentId());
			
			List<Institute> fields = new ArrayList<Institute>();
			for(model.entity.Institute i : entry.getValue()) {
				Institute institute = new Institute();
				institute.getDetails().setInstituteFullName(i.getInstituteDescription());
				
				institute.getDetails().setDepartment(dept);
				
				fields.add(institute);
			}
			
			list.put(dept, fields);
		}
		
		return list;
	}
	public void updateInstitute(Institute institute) throws Exception {
		model.entity.Institute entity = new model.entity.Institute();	
		
		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByFullName(institute.getDetails().getDepartment().getDetails().getDepartmentFullName());
		
		entity.setInstituteDescription(institute.getDetails().getInstituteFullName());
		entity.setInstituteName(institute.getDetails().getInstituteShortName());
		entity.setDepartmentId(dept.getDepartmentId());
		entity.setInstituteId(institute.getDetails().getId());
		
		int success = dao().update(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	private Institute createFromEntity(Institute base, model.entity.Institute entity) {
		Institute institute = new Institute();
		
		createFromEntity(entity, institute);
		
		return institute;
	}
	public void save(Institute institute) throws Exception {
		model.entity.Institute entity = new model.entity.Institute();	
		createEntity(institute, entity);
		
		int success = dao().save(entity);
		if(success == 0) {
			throw new Exception();
		}
	}	
}