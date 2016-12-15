package controller.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.BankDao;
import model.dao.interfaces.IBankDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Bank;

public class BankService extends GenericDaoService<BankDao, IBankDao> {
	private BankDao bank = null;
	
	
	public BankService() {
		this.bank = new BankDao();
	}

	
	@Override
	public BankDao getDao() {
		return bank;
	}
	@Override
	public IBankDao getDaoInterface() {
		return bank;
	}
	public Bank getByStudentId(Integer id) {	
		return bank.findByStudentId(id);
	}
	public Bank getByBankId(Integer id) {
		return bank.findByBankId(id);
	}
	public void updateBankNumber(Integer bankId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Bank entity = null;
		
		try {
			criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq("id.bankId", bankId));
			entity = (Bank)criteria.list().get(0);
			
			entity.setBankNumber(newValue);
			
			bank.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}