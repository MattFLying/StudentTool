package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import model.dao.interfaces.ISpecializationDao;
import model.entity.Entity;

@Service
public class SpecializationService extends DaoService<ISpecializationDao> {
	public SpecializationService() {
		super(DaoFactory.Dao.SPECIALIZATION);
	}
	
	
	@Override
	public ISpecializationDao getDao() {
		return (ISpecializationDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Specialization groupEntity = (model.entity.Specialization)entity;
		model.entity.SpecializationId id = new model.entity.SpecializationId();
		groupEntity.setId(id);
		
		Specialization specialization = (Specialization)base;
		
		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(specialization.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
		
		groupEntity.setSpecializationName(specialization.getDetails().getSpecializationName());
		groupEntity.getId().setFieldOfStudyId(field.getFieldOfStudyId());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Specialization groupEntity = (model.entity.Specialization)entity;
		Specialization specialization = (Specialization)base;
		
		FieldOfStudy field = new FieldOfStudyService().findFieldOfStudyNameById(groupEntity.getId().getFieldOfStudyId());
		
		specialization.getDetails().setSpecializationName(groupEntity.getSpecializationName());
		specialization.getDetails().setFieldOfStudy(field);
		specialization.getDetails().setId(groupEntity.getId().getSpecializationId());
	}
	private Specialization createFromEntity(Specialization base, model.entity.Specialization entity) {
		Specialization field = new Specialization();
		
		createFromEntity(entity, field);
		
		return field;
	}
	public Specialization findOneByName(String name) {
		model.entity.Specialization entity = dao().findByName(name);
		
		Specialization group = new Specialization();
		createFromEntity(entity, group);
		
		return group;
	}
	public Specialization findOneById(Integer id) {
		model.entity.Specialization entity = dao().findById(id);
		
		Specialization group = new Specialization();
		createFromEntity(entity, group);
		
		return group;
	}
	public List<Specialization> findByFieldOfStudyId(Integer id) {
		List<Specialization> list = new ArrayList<Specialization>();
		
		dao().findByFieldOfStudyId(id).forEach( (x) -> {
			list.add(createFromEntity(new Specialization(), x));
		});
		
		return list;
	}
	public List<Specialization> findByFieldOfStudyName(String name) {
		List<Specialization> list = new ArrayList<Specialization>();
		
		dao().findByFieldOfStudyName(name).forEach( (x) -> {
			list.add(createFromEntity(new Specialization(), x));
		});
		
		return list;
	}
	public List<Specialization> findSpecsByDepartmentId(Integer id) {
		List<Specialization> list = new ArrayList<Specialization>();		
		
		dao().findSpecsByDepartmentId(id).forEach( (x) -> {
			Specialization spec = new Specialization();
			
			spec.getDetails().setSpecializationName(x.getSpecializationName());
			
			list.add(spec);
		});
		
		return list;
	}
	public void save(Specialization specialization) throws Exception {
		model.entity.Specialization entity = new model.entity.Specialization();	
		createEntity(specialization, entity);
		
		int success = dao().save(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void update(Specialization specialization) throws Exception {
		model.entity.Specialization entity = new model.entity.Specialization();	
		createEntity(specialization, entity);
		
		int success = dao().update(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void delete(Specialization specialization) {
		model.entity.Specialization entity = new model.entity.Specialization();	
		createEntity(specialization, entity);
		
		dao().delete(entity);
	}
	public void updateSpecialization(Specialization specialization) throws Exception {
		model.entity.Specialization specializationEntity = new model.entity.Specialization();
		
		model.entity.SpecializationId id = new model.entity.SpecializationId();
		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(specialization.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());	
		
		id.setFieldOfStudyId(field.getFieldOfStudyId());
		id.setSpecializationId(specialization.getDetails().getId());
		specializationEntity.setId(id);
		specializationEntity.setSpecializationName(specialization.getDetails().getSpecializationName());
		
		int success = dao().update(specializationEntity);
		if(success == 0) {
			throw new Exception();
		}
	}
}