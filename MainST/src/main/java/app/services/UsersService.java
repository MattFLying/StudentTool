package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.user.User;
import model.dao.UserDetailsDao;
import model.dao.interfaces.IUserDao;
import model.entity.Entity;

/***
 * Class as service represents User. In this class contains all possible
 * operations with user and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class UsersService extends DaoService<IUserDao> {
	private UserDetailsDao detailsDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/***
	 * Default constructor sets basic fields.
	 */
	public UsersService() {
		super(DaoFactory.Dao.USER);
		this.detailsDao = new UserDetailsDao();
	}

	@Override
	public IUserDao getDao() {
		return (IUserDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Users userEntity = (model.entity.Users) entity;
		User user = (User) base;

		userEntity.setLogin(user.getLogin());
		userEntity.setPassword(user.getPassword());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Users userEntity = (model.entity.Users) entity;
		User user = (User) base;

		model.entity.UserDetails details = new UserDetailsDao().findByLogin(user.getLogin());
		user.setLogin(userEntity.getLogin());
		user.setPassword(userEntity.getPassword());
		user.setRole(details.getRole());
		user.setEnabled(userEntity.getEnabled());
	}

	/***
	 * Method to create user and save in database.
	 * 
	 * @param user
	 *            - user object
	 * @param enabled
	 *            - if user account should be anbled
	 * @throws Exception
	 */
	public void createBasicUser(User user, Integer enabled) throws Exception {
		model.entity.Users userEntity = new model.entity.Users();
		model.entity.UserDetails userDetailsEntity = new model.entity.UserDetails();

		userEntity.setLogin(user.getLogin());
		userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
		userEntity.setEnabled(enabled);
		userDetailsEntity.setLogin(user.getLogin());
		userDetailsEntity.setRole(user.getRole().getName());

		int success = dao().save(userEntity);
		this.detailsDao.save(userDetailsEntity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to change password of user.
	 * 
	 * @param user
	 *            - user object
	 * @throws Exception
	 */
	public void changePassword(User user) throws Exception {
		model.entity.Users userEntity = new model.entity.Users();

		userEntity.setLogin(user.getLogin());
		userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
		userEntity.setEnabled(1);

		int success = dao().update(userEntity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to find user by login.
	 * 
	 * @param login
	 *            - user login
	 * @return user object
	 */
	public User findUserByLogin(String login) {
		model.entity.Users userEntity = dao().findByLogin(login);
		User user = new User();
		user.setLogin(login);

		createFromEntity(userEntity, user);

		return user;
	}

	/***
	 * Method to update user login.
	 * 
	 * @param oldLogin
	 *            - old login of user
	 * @param newLogin
	 *            - new login of user
	 * @throws Exception
	 */
	public void updateUserLogin(String oldLogin, String newLogin) throws Exception {
		int success = dao().updateUser(oldLogin, newLogin);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update user activity.
	 * 
	 * @param user
	 *            - user object
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		int success = dao().updateUserEnabled(user.getEnabled(), user.getLogin());
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to save user in database.
	 * 
	 * @param user
	 *            - user object
	 * @throws Exception
	 */
	public void save(User user) throws Exception {
		model.entity.Users entity = new model.entity.Users();
		createEntity(user, entity);

		model.entity.UserDetails details = new model.entity.UserDetails();
		details.setRole(user.getRole().getName());
		details.setLogin(user.getLogin());

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
		success = new UserDetailsDao().save(details);
		if (success == 0) {
			throw new Exception();
		}
	}
}