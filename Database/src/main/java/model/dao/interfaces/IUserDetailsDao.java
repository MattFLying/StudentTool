package model.dao.interfaces;

import model.entity.UserDetails;

public interface IUserDetailsDao extends IGenericDao<UserDetails, Integer> {
	UserDetails findByLogin(String login);
}