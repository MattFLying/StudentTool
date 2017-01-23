package app.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
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
@Service
public class UsersService extends DaoService<IUserDao> {
	private UserDetailsDao detailsDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public UsersService() {
		super(DaoFactory.Dao.USER);
		this.detailsDao = new UserDetailsDao();
	}
	
	
	@Override
	public IUserDao getDao() {
		return (IUserDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Users userEntity = (model.entity.Users)entity;
		User user = (User)base;
		
		userEntity.setLogin(user.getLogin());
		userEntity.setPassword(user.getPassword());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Users userEntity = (model.entity.Users)entity;
		User user = (User)base;
		
		model.entity.UserDetails details = new UserDetailsDao().findByLogin(user.getLogin());
		
		user.setLogin(userEntity.getLogin());
		user.setPassword(userEntity.getPassword());
		user.setRole(details.getRole());
	}
	
	
	public void createBasicUser(User user) {
		model.entity.Users userEntity = new model.entity.Users();
		model.entity.UserDetails userDetailsEntity = new model.entity.UserDetails();
		
		userEntity.setLogin(user.getLogin());
		userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
		userEntity.setEnabled(1);
		userDetailsEntity.setLogin(user.getLogin());
		userDetailsEntity.setRole(user.getRole().getName());
		
		dao().save(userEntity);
		this.detailsDao.save(userDetailsEntity);
	}
	
	
	public void changePassword(User user) {
		model.entity.Users userEntity = new model.entity.Users();
		
		userEntity.setLogin(user.getLogin());
		userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
		userEntity.setEnabled(1);
		
		dao().update(userEntity);
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
	
	
	
	public void save(User user) {
		model.entity.Users entity = new model.entity.Users();	
		createEntity(user, entity);
		
		model.entity.UserDetails details = new model.entity.UserDetails();
		details.setRole(user.getRole().getName());
		details.setLogin(user.getLogin());
		
		dao().save(entity);
		new UserDetailsDao().save(details);
	}
	public void update(User user) {
		model.entity.Users entity = new model.entity.Users();	
		createEntity(user, entity);
		
		dao().update(entity);
	}
	public void delete(User user) {
		model.entity.Users entity = new model.entity.Users();	
		createEntity(user, entity);
		
		dao().delete(entity);
	}
}