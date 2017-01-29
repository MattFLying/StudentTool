package model.dao.interfaces;

import model.entity.UserDetails;

/***
 * Interface represents user details database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IUserDetailsDao extends IGenericDao<UserDetails, Integer> {
	/***
	 * Method to search details for user by login.
	 * 
	 * @param login
	 *            - login of user
	 * @return user details object
	 */
	UserDetails findByLogin(String login);
}