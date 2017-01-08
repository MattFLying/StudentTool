package app.services;

import app.services.factory.DaoFactory;
import core.humanity.details.Address;
import model.dao.interfaces.IAddressDao;
import model.entity.Entity;

public class AddressService extends DaoService<IAddressDao> {
	public AddressService() {
		super(DaoFactory.Dao.ADDRESS);
	}
	
	
	@Override
	public IAddressDao getDao() {
		return (IAddressDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Address entityAddress = (model.entity.Address)entity;	
		Address address = (Address)base;
		
		entityAddress.setAddressCity(address.getCity());
		entityAddress.setAddressCode(address.getPostalCode());
		entityAddress.setAddressStreet(address.getStreetFullAddress());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Address entityAddress = (model.entity.Address)entity;	
		Address address = (Address)base;
		
		address.setCity(entityAddress.getAddressCity());
		address.setPostalCode(entityAddress.getAddressCode());
		address.setStreetFullAddress(entityAddress.getAddressStreet());
	}
	public Address findOneById(int id) {
		model.entity.Address entity = dao().findById(id);
		
		Address address = new Address();
		createFromEntity(entity, address);
		
		return address;
	}
	public void save(Address address) {
		model.entity.Address entity = new model.entity.Address();	
		createEntity(address, entity);
		
		dao().save(entity);
	}
	public void update(Address address) {
		model.entity.Address entity = new model.entity.Address();	
		createEntity(address, entity);
		
		dao().update(entity);
	}
	public void delete(Address address) {
		model.entity.Address entity = new model.entity.Address();	
		createEntity(address, entity);
		
		dao().delete(entity);
	}
}