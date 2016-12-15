package model.dao.interfaces;

import model.entity.Certificate;
import model.entity.CertificateId;

public interface ICertificateDao extends IGenericDao<Certificate, CertificateId> {
	Certificate findByTeacherId(Integer id);
	Certificate findByCertificateId(Integer id);
	Certificate findByCertificate(String certificate);
}