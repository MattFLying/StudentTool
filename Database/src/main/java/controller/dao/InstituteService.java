package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.InstituteDao;
import model.dao.interfaces.IInstituteDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Institute;

public class InstituteService extends GenericDaoService<InstituteDao, IInstituteDao> {
	private InstituteDao institute = null;
	
	
	public InstituteService() {
		this.institute = new InstituteDao();
	}


	@Override
	public InstituteDao getDao() {
		return institute;
	}
	@Override
	public IInstituteDao getDaoInterface() {
		return institute;
	}
	public Institute getByShortName(String name) {
		return institute.findByName(name);
	}
	public Institute getByFullName(String name) {
		return institute.findByFullName(name);
	}
	public List<Institute> getAllByDepartmentId(Integer id) {
		return institute.findByDepartmentId(id);
	}
	public List<Institute> getAllByDepartmentName(String name) {
		return institute.findByDepartmentName(name);
	}
	public void updateShortName(Integer instituteId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Institute entity = null;
		
		try {
			criteria = session.createCriteria(Institute.class);
			criteria.add(Restrictions.eq("instituteId", instituteId));
			entity = (Institute)criteria.list().get(0);
			
			entity.setInstituteName(newValue);
			
			institute.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateFullName(Integer instituteId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Institute entity = null;
		
		try {
			criteria = session.createCriteria(Institute.class);
			criteria.add(Restrictions.eq("instituteId", instituteId));
			entity = (Institute)criteria.list().get(0);
			
			entity.setInstituteDescription(newValue);
			
			institute.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}