package model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.ICertificateDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Certificate;
import model.entity.CertificateId;

public class CertificateDao extends GenericDao<Certificate, CertificateId> implements ICertificateDao {
	private Certificate certificateEntity;
	
	
	public CertificateDao() {
		this.certificateEntity = null;
	}


	public Certificate findByTeacherId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Certificate.class);
			criteria.add(Restrictions.eq("id.teacherId", id));
			certificateEntity = (Certificate)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		if(certificateEntity != null) {
			return certificateEntity;
		} else {
			return null;
		}
	}
	public Certificate findByCertificateId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Certificate.class);
			criteria.add(Restrictions.eq("id.certificateId", id));
			certificateEntity = (Certificate)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		if(certificateEntity != null) {
			return certificateEntity;
		} else {
			return null;
		}
	}
	public Certificate findByCertificate(String certificate) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Certificate.class);
			criteria.add(Restrictions.eq("certificateName", certificate));
			certificateEntity = (Certificate)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		if(certificateEntity != null) {
			return certificateEntity;
		} else {
			return null;
		}
	}
}