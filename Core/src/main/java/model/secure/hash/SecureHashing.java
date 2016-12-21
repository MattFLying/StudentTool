package model.secure.hash;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class SecureHashing implements HashCommand {
	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
	protected static final int HASH_BYTE_SIZE = 64 * 8;
	protected static final int SALT_BYTE_SIZE = 64 * 8;
	protected int iterations;
	protected HashObject hashObj;
	
	
	protected SecureHashing(HashObject hashObj) {
		this.hashObj= hashObj;
	}
	
	
	protected String fromHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bigInteger = new BigInteger(1, array);
		String hex = bigInteger.toString(16);
		
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
	protected byte[] toHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		
		return bytes;
	}
	protected boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++) {
			diff |= a[i] ^ b[i];
		}

		return diff == 0;
	}
	protected byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
		
		byte[] salt = new byte[SALT_BYTE_SIZE];
		secureRandom.nextBytes(salt);
		
		return salt;
	}
	protected byte[] encode(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterations, bytes);
		SecretKeyFactory secretKey = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		
		return secretKey.generateSecret(pbeKeySpec).getEncoded();
	}
	protected int generateIterations() {
		return iterations = new Random().nextInt(30000 - 3000) + 3000;
	}
	public HashObject getHashObj() {
		return hashObj;
	}
	public void setHashObj(HashObject hashObj) {
		this.hashObj = hashObj;
	}
}