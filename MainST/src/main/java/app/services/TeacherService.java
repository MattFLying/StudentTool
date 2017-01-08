package app.services;

import app.services.factory.DaoFactory;
import model.dao.interfaces.ITeacherDao;
import model.entity.Entity;

public class TeacherService extends DaoService<ITeacherDao> {
	public TeacherService() {
		super(DaoFactory.Dao.TEACHER);
		dao();
	}
	
	
	@Override
	public ITeacherDao getDao() {
		return (ITeacherDao)dao;
	}


	@Override
	protected void createEntity(Object base, Entity entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void createFromEntity(Entity entity, Object base) {
		// TODO Auto-generated method stub
		
	}
	
}