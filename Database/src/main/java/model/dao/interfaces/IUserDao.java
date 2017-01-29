package model.dao.interfaces;

import model.entity.Users;

/***
 * Interface represents user database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IUserDao extends IGenericDao<Users, Integer> {
	/***
	 * Method to search user by login.
	 * 
	 * @param login
	 *            - login of user
	 * @return
	 */
	Users findByLogin(String login);

	/***
	 * Method to update user login.
	 * 
	 * @param oldLogin
	 *            - old login of user
	 * @param newLogin
	 *            - new login of user
	 * @return 0 - user account is disabled 1 - user account is enabled
	 */
	int updateUser(String oldLogin, String newLogin);

	/***
	 * Method to update user activity.
	 * 
	 * @param enabled
	 *            - activity of user by integer values
	 * @param login
	 *            - login of user
	 * @return 0 - operation failed 1 - operation successful
	 */
	int updateUserEnabled(Integer enabled, String login);
}