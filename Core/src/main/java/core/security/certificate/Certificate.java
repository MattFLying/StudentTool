package core.security.certificate;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

public class Certificate extends CertificateAbstract {
	private static final String CERTIFICATES_PATH = "F://GitHub/Engineering_Work/StudentTool/Core/certificates/";
	private static final String CERTIFICATES_FILE_TYPE = ".uzcrt";

	
	public Certificate() {}


	public void generateCertificate(String certOwner, String initials, int yearsValidity, String password) {
		try {
			CertAndKeyGen keyGen = generateKey();

			X500Name owner = createNameCertificate("CN=Teacher, surName=" + certOwner + ", initials=" + initials);

			X509Certificate cert = keyGen.getSelfCertificate(owner, (long) 365 * 24 * 60 * 60 * yearsValidity);

			cert = createSignedCertificate(cert, getRootCert(), getKey().getPrivate());
			System.out.println(cert);
			saveCert(cert, keyGen.getPrivateKey(), buildAlias(certOwner), password, CERTIFICATES_PATH + buildAlias(certOwner) + CERTIFICATES_FILE_TYPE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public boolean validateCertificate(InputStream inputStream, String password, String alias) {
		try {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(inputStream, password.toCharArray());
			
			X509Certificate cert = (X509Certificate) keystore.getCertificate(buildAlias(alias));
			
			Date date = new Date();
			if(cert != null && date.before(cert.getNotAfter())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
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
	private String buildAlias(String certOwner) {
		String[] ownerParts = certOwner.split(" ");
		
		String owner = "";
		
		for(String string : ownerParts) {
			owner = owner.concat(string);
		}
		
		return owner;
	}
}