package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IUserDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Users;
import model.entity.UserDetails;

public class UserDao extends GenericDao<Users, Integer> implements IUserDao {
	private Users userEntity;
	
	
	public UserDao(){
		this.userEntity = null;
	}


	public Users findByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Users.class);
			criteria.add(Restrictions.eq("login", login));
			userEntity = (Users)criteria.list().get(0);
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