package model.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, ID extends Serializable> {
	void save(T entity);
	void update(T entity);
	void delete(T entity);
	void deleteAll(List<T> entities);
	T findById(ID id);
	List<T> findAll(Class<T> clazz);
}