package model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IBankDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Bank;
import model.entity.BankId;

public class BankDao extends GenericDao<Bank, BankId> implements IBankDao {
	private Bank bankEntity;
	
	
	public BankDao() {
		this.bankEntity = null;
	}


	public Bank findByStudentId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq("id.studentId", id));
			bankEntity = (Bank)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		if(bankEntity != null) {
			return bankEntity;
		} else {
			return null;
		}
	}
	public Bank findByBankId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq("id.bankId", id));
			bankEntity = (Bank)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		if(bankEntity != null) {
			return bankEntity;
		} else {
			return null;
		}
	}
}