package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IUserDao;
import model.db.hib.util.HibernateUtil;
import model.entity.User;

public class UserDao extends GenericDao<User, Integer> implements IUserDao {
	private User userEntity;
	
	
	public UserDao(){
		this.userEntity = null;
	}


	public User findByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userLogin", login));
			userEntity = (User)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(userEntity != null) {
			return userEntity;
		} else {
			return null;
		}
	}
}