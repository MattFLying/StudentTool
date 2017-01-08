package app.services;

import java.util.ArrayList;
import java.util.List;

import app.services.factory.DaoFactory;
import core.humanity.details.BankAccount;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.grade.Grade;
import model.dao.interfaces.IGradeDao;
import model.entity.Entity;

public class GradeService extends DaoService<IGradeDao> {
	public GradeService() {
		super(DaoFactory.Dao.GRADE);
	}
	
	
	@Override
	public IGradeDao getDao() {
		return (IGradeDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Grade bankEntity = (model.entity.Grade)entity;
		Grade grade = (Grade)base;
		
		
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Grade bankEntity = (model.entity.Grade)entity;
		Grade grade = (Grade)base;
		
		
	}
	public Grade findOneByStudentId(int id) {
		model.entity.Grade entity = dao().findByGradeId(id);
		
		Grade grade = new Grade();
		createFromEntity(entity, grade);
		
		return grade;
	}
	
	
	
	public void save(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().save(entity);
	}
	public void update(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().update(entity);
	}
	public void delete(Grade grade) {
		model.entity.Grade entity = new model.entity.Grade();	
		createEntity(grade, entity);
		
		dao().delete(entity);
	}
}