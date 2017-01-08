package model.security.hash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import model.security.base.HashObject;

public class CertificateHashing extends HashObject {
	
	
	public CertificateHashing(String originalValue) {
		super(originalValue);
	}
	
	
	public boolean validate() throws NoSuchAlgorithmException, InvalidKeySpecException {
		return false;
	}
	public void generateHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
	}
}