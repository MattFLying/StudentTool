package app.services;

import app.services.factory.DaoFactory;
import model.dao.interfaces.IGenericDao;
import model.entity.Entity;

/***
 * Abstract basic class represents services using in spring controllers. Each
 * service should extend this class.
 * 
 * @author Mateusz Mucha
 *
 * @param <T>
 *            - interface of specific type on which all oeprations will be done
 */
public abstract class DaoService<T extends IGenericDao<?, ?>> {
	protected DaoFactory factory = new DaoFactory();
	protected IGenericDao<?, ?> dao;

	/***
	 * Default constructor sets specific dao object type.
	 * 
	 * @param type
	 *            - DAO interface object type
	 */
	protected DaoService(DaoFactory.Dao type) {
		this.dao = factory.get(type);
	}

	/***
	 * Method to get object type using in services.
	 * 
	 * @return specific dao object type
	 */
	protected T dao() {
		return (T) dao;
	}

	/***
	 * Method to build details of entity from main object. This method prepares
	 * entities to entry to database.
	 * 
	 * @param base
	 *            - base main object type
	 * @param entity
	 *            - entity type
	 */
	protected abstract void createEntity(Object base, Entity entity);

	/***
	 * Method to build details of main object type from entity.
	 * 
	 * @param entity
	 *            - entity type
	 * @param base
	 *            - base main object type
	 */
	protected abstract void createFromEntity(Entity entity, Object base);

	/***
	 * Method allows to get specific DAO object type from service.
	 * 
	 * @return specific dao object
	 */
	public abstract T getDao();
}