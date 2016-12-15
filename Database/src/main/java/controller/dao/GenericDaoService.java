package controller.dao;

public abstract class GenericDaoService<Dao, DaoInterface> {
	public abstract Dao getDao();
	public abstract DaoInterface getDaoInterface();
}