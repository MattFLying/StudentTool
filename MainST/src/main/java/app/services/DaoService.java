package app.services;

import app.services.factory.DaoFactory;
import model.dao.interfaces.IGenericDao;
import model.entity.Entity;

public abstract class DaoService<T extends IGenericDao<?, ?>> {
	protected DaoFactory factory = new DaoFactory();
	protected IGenericDao<?, ?> dao;
	
	
	protected DaoService(DaoFactory.Dao type) {
		this.dao = factory.get(type);
	}
	

	protected T dao() {
		return (T)dao;
	}
	public abstract T getDao();
	protected abstract void createEntity(Object base, Entity entity);
	protected abstract void createFromEntity(Entity entity, Object base);
}