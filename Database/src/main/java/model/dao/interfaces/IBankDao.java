package model.dao.interfaces;

import model.entity.Bank;
import model.entity.BankId;

public interface IBankDao extends IGenericDao<Bank, BankId> {
	Bank findByStudentId(Integer id);
	Bank findByBankId(Integer id);
}