package model.security.certificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertInfo;

public abstract class CertificateAbstract {
	private static final String KEY_TYPE = "RSA";
	private static final String ALGORITHM_SIGNATURE = "SHA1WithRSA";
	private static final int KEY_BYTE_SIZE = 1024;
		
	
	protected static X500Name createNameCertificate(String value) throws IOException {
		return new X500Name(value);
	}
	protected static void createCertificateInfo(CertAndKeyGen keyGen, X500Name issuer, X500Name owner) throws CertificateException, IOException {
		X509CertInfo info = new X509CertInfo();
		info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(new BigInteger(64, new SecureRandom())));
		info.set(X509CertInfo.ISSUER, issuer);
		info.set(X509CertInfo.SUBJECT, owner);
		info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
		info.set(X509CertInfo.KEY, new CertificateX509Key(keyGen.getPublicKey()));
	}
	protected static CertAndKeyGen generateKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
		CertAndKeyGen keyGen = new CertAndKeyGen(KEY_TYPE, ALGORITHM_SIGNATURE, null);
		keyGen.generate(KEY_BYTE_SIZE);
		
		return keyGen;
	}
	protected static void saveCert(X509Certificate cert, PrivateKey key, String alias, String password, String fileName) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");
		
		keyStore.load(null, null);
		keyStore.setKeyEntry(alias, key, password.toCharArray(), new java.security.cert.Certificate[] { cert });
		
		File file = new File(".", fileName);
		keyStore.store(new FileOutputStream(file), password.toCharArray());
	}
	protected static KeyPair getKey() {
		try {
			FileInputStream inputStream = new FileInputStream(Root.CERTIFICATE_FILE_NAME);

			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(inputStream, Root.CERTIFICATE_FILE_PASSWORD.toCharArray());

			Key key = keystore.getKey(Root.CERTIFICATE_FILE_ALIAS, Root.CERTIFICATE_FILE_PASSWORD.toCharArray());
			if (key instanceof PrivateKey) {
				java.security.cert.Certificate cert = keystore.getCertificate(Root.CERTIFICATE_FILE_ALIAS);

				PublicKey publicKey = cert.getPublicKey();

				KeyPair keyPair = new KeyPair(publicKey, (PrivateKey) key);
				
				return keyPair;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	protected static X509Certificate getRootCert() {
		try {
			FileInputStream inputStream = new FileInputStream(Root.CERTIFICATE_FILE_NAME);

			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(inputStream, Root.CERTIFICATE_FILE_PASSWORD.toCharArray());

			return (X509Certificate) keystore.getCertificate(Root.CERTIFICATE_FILE_ALIAS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	private static class Root {
		private static final String CERTIFICATE_NAME = "CN=University Employee, L=Zielona Góra, C=PL, OU=Uniwersytet Zielonogórski";
		private static final String CERTIFICATE_FILE_NAME = "root_cert.uzcrt";
		private static final String CERTIFICATE_FILE_ALIAS = "root_uz_cert";
		private static final String CERTIFICATE_FILE_PASSWORD = "UZ_Cert_Root";
		private static final long CERTIFICATE_VALIDITY = 365 * 24 * 60 * 60 * 10;
		
		private static void generateRootCertificate() {
			try {
				CertAndKeyGen keyGen = generateKey();
				X500Name issuer = createNameCertificate(CERTIFICATE_NAME);

				createCertificateInfo(keyGen, issuer, issuer);
				
				X509Certificate cert = keyGen.getSelfCertificate(issuer, CERTIFICATE_VALIDITY);
				
				saveCert(cert, keyGen.getPrivateKey(), CERTIFICATE_FILE_ALIAS, CERTIFICATE_FILE_PASSWORD, CERTIFICATE_FILE_NAME);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}