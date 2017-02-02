package model.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IUserDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Users;

/***
 * DAO class represents user for all operations on this type.
 * 
 * @author Mateusz Mucha
 *
 */
public class UserDao extends GenericDao<Users, Integer> implements IUserDao {
	private Users userEntity;

	/***
	 * Default construtor sets basic field using in this class.
	 */
	public UserDao() {
		this.userEntity = null;
	}

	public Users findByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;

		try {
			criteria = session.createCriteria(Users.class);
			criteria.add(Restrictions.eq("login", login));
			userEntity = (Users) criteria.list().get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (userEntity != null) {
			return userEntity;
		} else {
			return null;
		}
	}

	public int updateUserEnabled(Integer enabled, String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = null;

			Query query = session.createQuery("UPDATE Users SET enabled=? WHERE login=?");
			query.setInteger(0, enabled);
			query.setString(1, login);

			transaction = session.beginTransaction();
			query.executeUpdate();
			transaction.commit();

			return 1;
		} catch (Exception e) {
			e.getStackTrace();

			return 0;
		} finally {
			session.clear();
			session.close();
		}
	}

	public int updateUser(String oldLogin, String newLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = null;

			Query query = session.createQuery("UPDATE Users SET login=? WHERE login=?");
			query.setString(0, newLogin);
			query.setString(1, oldLogin);

			transaction = session.beginTransaction();
			query.executeUpdate();
			transaction.commit();

			return 1;
		} catch (Exception e) {
			e.getStackTrace();

			return 0;
		} finally {
			session.clear();
		}
	}
}