package app.services;

import org.springframework.stereotype.Service;

import app.services.factory.DaoFactory;
import core.humanity.details.Address;
import model.dao.interfaces.IAddressDao;
import model.entity.Entity;

/***
 * Class as service represents Address. In this class contains all possible
 * operations with address and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class AddressService extends DaoService<IAddressDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public AddressService() {
		super(DaoFactory.Dao.ADDRESS);
	}

	@Override
	public IAddressDao getDao() {
		return (IAddressDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Address entityAddress = (model.entity.Address) entity;
		Address address = (Address) base;

		entityAddress.setAddressCity(address.getCity());
		entityAddress.setAddressCode(address.getPostalCode());
		entityAddress.setAddressStreet(address.getStreetFullAddress());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Address entityAddress = (model.entity.Address) entity;
		Address address = (Address) base;

		address.setCity(entityAddress.getAddressCity());
		address.setPostalCode(entityAddress.getAddressCode());
		address.setStreetFullAddress(entityAddress.getAddressStreet());
	}

	/***
	 * Method to search address by specific identificator.
	 * 
	 * @param id
	 *            - address identificator
	 * @return address object
	 */
	public Address findOneById(int id) {
		model.entity.Address entity = dao().findById(id);

		Address address = new Address();
		createFromEntity(entity, address);

		return address;
	}

	/***
	 * Method to save address in database returning his identificator value.
	 * 
	 * @param address
	 *            - address type to convert into entity
	 * @return identificator of saving address
	 */
	public int saveWithId(Address address) {
		model.entity.Address entity = new model.entity.Address();
		createEntity(address, entity);

		dao().save(entity);

		return entity.getAddressId();
	}
}