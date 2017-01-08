package model.dao.interfaces;

import model.entity.Users;

public interface IUserDao extends IGenericDao<Users, Integer> {
	Users findByLogin(String login);
}