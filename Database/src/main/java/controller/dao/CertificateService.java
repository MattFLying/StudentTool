package controller.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.CertificateDao;
import model.dao.interfaces.ICertificateDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Certificate;

public class CertificateService extends GenericDaoService<CertificateDao, ICertificateDao> {
	private CertificateDao certificate = null;
	
	
	public CertificateService() {
		this.certificate = new CertificateDao();
	}
	
	
	@Override
	public CertificateDao getDao() {
		return certificate;
	}
	@Override
	public ICertificateDao getDaoInterface() {
		return certificate;
	}
	public Certificate getByTeacherId(Integer id) {
		return certificate.findByTeacherId(id);
	}
	public Certificate getByCertificateId(Integer id) {
		return certificate.findByCertificateId(id);
	}
	public Certificate getByCertificate(String value) {
		return certificate.findByCertificate(value);
	}
	public void updateCertificate(Integer certificateId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Certificate entity = null;
		
		try {
			criteria = session.createCriteria(Certificate.class);
			criteria.add(Restrictions.eq("id.certificateId", newValue));
			entity = (Certificate)criteria.list().get(0);
			
			entity.setCertificateName(newValue);
			
			certificate.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}