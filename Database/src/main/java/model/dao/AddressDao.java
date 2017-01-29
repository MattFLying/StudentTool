package model.dao;

import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IAddressDao;
import model.entity.Address;

/***
 * DAO class represents address for all operations on this type.
 * 
 * @author Mateusz Mucha
 *
 */
public class AddressDao extends GenericDao<Address, Integer> implements IAddressDao {
	/***
	 * Default construtor sets basic field using in this class.
	 */
	public AddressDao() {
		super();
	}
}