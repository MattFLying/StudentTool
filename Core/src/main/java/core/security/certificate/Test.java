package core.security.certificate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.cert.X509Certificate;

public class Test {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Certificate certificate = new Certificate();
		
		certificate.generateCertificate("mgr. Jan Kowalski", "J.K.", 5, "haslo");
		//InputStream inputStream = new FileInputStream("F://GitHub/Engineering_Work/StudentTool/Core/certificates/mgr.JanAdamczyk.uzcrt");
		//System.out.println(certificate.validateCertificate(inputStream, "haslo", "mgr.JanAdamczyk"));
	}
	
	
	
}