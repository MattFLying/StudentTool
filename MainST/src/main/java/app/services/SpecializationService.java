package app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import model.dao.SpecializationDao;
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
	public void save(Specialization specialization) {
		model.entity.Specialization entity = new model.entity.Specialization();	
		createEntity(specialization, entity);
		
		dao().save(entity);
	}
	public void update(Specialization specialization) {
		model.entity.Specialization entity = new model.entity.Specialization();	
		createEntity(specialization, entity);
		
		dao().update(entity);
	}
	public void delete(Specialization specialization) {
		model.entity.Specialization entity = new model.entity.Specialization();	
		createEntity(specialization, entity);
		
		dao().delete(entity);
	}
	
	
	
	
	
	
	
	
	
	
	
}