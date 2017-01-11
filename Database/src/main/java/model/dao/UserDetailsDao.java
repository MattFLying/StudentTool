package model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IUserDetailsDao;
import model.db.hib.util.HibernateUtil;
import model.entity.UserDetails;

public class UserDetailsDao extends GenericDao<UserDetails, Integer> implements IUserDetailsDao {
	private UserDetails userEntity;
	
	
	public UserDetailsDao(){
		this.userEntity = null;
	}
	
	
	public UserDetails findByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("login", login));
			userEntity = (UserDetails)criteria.list().get(0);
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