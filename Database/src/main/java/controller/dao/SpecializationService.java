package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.SpecializationDao;
import model.dao.interfaces.ISpecializationDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Specialization;

public class SpecializationService extends GenericDaoService<SpecializationDao, ISpecializationDao> {
	private SpecializationDao specialization = null;
	
	
	public SpecializationService() {
		this.specialization = new SpecializationDao();
	}


	@Override
	public SpecializationDao getDao() {
		return specialization;
	}
	@Override
	public ISpecializationDao getDaoInterface() {
		return specialization;
	}
	public Specialization getByName(String name) {
		return specialization.findByName(name);
	}
	public List<Specialization> getByFieldOfStudyId(Integer id) {
		return specialization.findByFieldOfStudyId(id);
	}
	public List<Specialization> getByFieldOfStudyName(String name) {
		return specialization.findByFieldOfStudyName(name);
	}
	public void updateName(Integer specializationId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Specialization entity = null;
		
		try {
			criteria = session.createCriteria(Specialization.class);
			criteria.add(Restrictions.eq("id.specializationId", specializationId));
			entity = (Specialization)criteria.list().get(0);
			
			entity.setSpecializationName(newValue);
			
			specialization.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}