package app.services;

import app.services.factory.DaoFactory;
import core.humanity.details.BankAccount;
import model.dao.interfaces.IBankDao;
import model.entity.Entity;

public class BankService extends DaoService<IBankDao> {
	public BankService() {
		super(DaoFactory.Dao.BANK);
	}
	
	
	@Override
	public IBankDao getDao() {
		return (IBankDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Bank bankEntity = (model.entity.Bank)entity;
		BankAccount bank = (BankAccount)base;
		
		bankEntity.setBankNumber(bank.getNumber());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Bank bankEntity = (model.entity.Bank)entity;
		BankAccount bank = (BankAccount)base;
		
		bank.setNumber(bankEntity.getBankNumber());
	}
	public BankAccount findOneByStudentId(int id) {
		model.entity.Bank entity = dao().findByStudentId(id);
		
		BankAccount bank = new BankAccount();
		createFromEntity(entity, bank);
		
		return bank;
	}
	public void save(BankAccount bank) {
		model.entity.Bank entity = new model.entity.Bank();	
		createEntity(bank, entity);
		
		dao().save(entity);
	}
	public void update(BankAccount bank) {
		model.entity.Bank entity = new model.entity.Bank();	
		createEntity(bank, entity);
		
		dao().update(entity);
	}
	public void delete(BankAccount bank) {
		model.entity.Bank entity = new model.entity.Bank();	
		createEntity(bank, entity);
		
		dao().delete(entity);
	}
}