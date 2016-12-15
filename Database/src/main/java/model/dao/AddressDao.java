package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IAddressDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Address;

public class AddressDao extends GenericDao<Address, Integer> implements IAddressDao {
	public AddressDao() {
		super();
	}
	
	
	public List<Address> findAllByCity(String city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Address> list = new ArrayList<Address>();
		
		try {
			criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressCity", city));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Address> findAllByCode(String code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Address> list = new ArrayList<Address>();
		
		try {
			criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressCode", code));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}