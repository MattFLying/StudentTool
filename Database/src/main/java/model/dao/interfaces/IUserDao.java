package model.dao.interfaces;

import model.entity.User;

public interface IUserDao extends IGenericDao<User, Integer> {
	User findByLogin(String login);
}