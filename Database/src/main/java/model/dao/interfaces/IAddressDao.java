package model.dao.interfaces;

import java.util.List;
import model.entity.Address;

public interface IAddressDao extends IGenericDao<Address, Integer> {
	List<Address> findAllByCity(String city);
	List<Address> findAllByCode(String code);
}