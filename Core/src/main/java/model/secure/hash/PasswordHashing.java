package model.secure.hash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashing extends SecureHashing {
	
	
	public PasswordHashing(HashObject hashObj) {
		super(hashObj);
	}
	
	
	public boolean validate() throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] password = toHex(hashObj.getStoredValue());
		byte[] salt = toHex(hashObj.getStoredSalt());
		
		byte[] validate = encode(hashObj.getOriginalValue().toCharArray(), salt, hashObj.getStoredIterations(), HASH_BYTE_SIZE);

		return slowEquals(password, validate);
	}

	public void generateHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = generateSalt();
		byte[] hash = encode(hashObj.getOriginalValue().toCharArray(), salt, generateIterations(), HASH_BYTE_SIZE);
		
		hashObj.configure(fromHex(hash), fromHex(salt), this.iterations);
	}
}