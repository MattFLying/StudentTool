package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.AddressDao;
import model.dao.interfaces.IAddressDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Address;

public class AddressService extends GenericDaoService<AddressDao, IAddressDao> {
	private AddressDao address = null;
	
	
	public AddressService() {
		this.address = new AddressDao();
	}
	
	
	@Override
	public AddressDao getDao() {
		return address;
	}
	@Override
	public IAddressDao getDaoInterface() {
		return address;
	}
	public List<Address> getAddressesByCity(String city) {
		return address.findAllByCity(city);
	}
	public List<Address> getAddressesByCode(String code) {
		return address.findAllByCode(code);
	}
	public void updateCity(Integer addressId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Address entity = null;
		
		try {
			criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressId", addressId));
			entity = (Address)criteria.list().get(0);
			
			entity.setAddressCity(newValue);
			
			address.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateCode(Integer addressId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Address entity = null;
		
		try {
			criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressId", addressId));
			entity = (Address)criteria.list().get(0);
			
			entity.setAddressCode(newValue);
			
			address.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateStreet(Integer addressId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Address entity = null;
		
		try {
			criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressId", addressId));
			entity = (Address)criteria.list().get(0);
			
			entity.setAddressStreet(newValue);
			
			address.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFullAddress(Integer addressId, String city, String street, String code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Address entity = null;
		
		try {
			criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressId", addressId));
			entity = (Address)criteria.list().get(0);
			
			entity.setAddressCity(city);
			entity.setAddressStreet(street);
			entity.setAddressCode(code);
			
			address.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}