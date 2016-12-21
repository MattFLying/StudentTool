package model.secure.certificate;

import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

public class Certificate extends CertificateAbstract {

	public Certificate() {

	}

	private X509Certificate generateCertificate() {
		try {
			CertAndKeyGen keyGen = generateKey();

			X500Name owner = createNameCertificate("CN=Teacher, surName=Jakis, initials=T.T.");

			X509Certificate cert = keyGen.getSelfCertificate(owner, (long) 365 * 24 * 60 * 60);

			cert = createSignedCertificate(cert, getRootCert(), getKey().getPrivate());

			return cert;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	private X509Certificate createSignedCertificate(X509Certificate cetrificate, X509Certificate issuerCertificate, PrivateKey issuerPrivateKey) {
		try {
			Principal issuer = issuerCertificate.getSubjectDN();
			String issuerSignatureAlgorithm = issuerCertificate.getSigAlgName();

			byte[] certBytes = cetrificate.getTBSCertificate();
			X509CertInfo info = new X509CertInfo(certBytes);
			info.set(X509CertInfo.ISSUER, (X500Name)issuer);

			X509CertImpl outCert = new X509CertImpl(info);
			outCert.sign(issuerPrivateKey, issuerSignatureAlgorithm);

			return outCert;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}