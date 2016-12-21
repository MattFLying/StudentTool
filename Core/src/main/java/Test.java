import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import model.secure.hash.HashCommand;
import model.secure.hash.HashObject;
import model.secure.hash.PasswordHashing;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		/*String originalPassword = "password";
		String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);

		boolean matched = validatePassword("password", generatedSecuredPasswordHash);
		System.out.println(matched);

		matched = validatePassword("password1", generatedSecuredPasswordHash);
		System.out.println(matched);

		System.out.println("Original: " + originalPassword + "\nHash: " + generatedSecuredPasswordHash);*/
		
		/*PasswordHashing secure = new PasswordHashing();
		String original = "hasLo_123";
		
		String[] hash_1 = secure.generateStorngPasswordHash(original);
		String[] hash_2 = secure.generateStorngPasswordHash(original);
		String[] hash_3 = secure.generateStorngPasswordHash(original);
		
		int iter1 = Integer.valueOf(hash_1[0]);
		String hashedPassword1 = hash_1[1];
		String hashedSalt1 = hash_1[2];
		
		int iter2 = Integer.valueOf(hash_2[0]);
		String hashedPassword2 = hash_2[1];
		String hashedSalt2 = hash_2[2];
		
		int iter3 = Integer.valueOf(hash_3[0]);
		String hashedPassword3 = hash_3[1];
		String hashedSalt3 = hash_3[2];
		
		
		System.out.println(
				"Original: " + original +
				"\nIterations: " + iter1 +
				"\nStored password: " + hashedPassword1 +
				"\nStored salt: " + hashedSalt1 + 
				"\nCompare: " + secure.validatePassword("hasLo_123", hashedPassword1, hashedSalt1, iter1)
		);
		System.out.println(
				"\nOriginal: " + original +
				"\nIterations: " + iter2 +
				"\nStored password: " + hashedPassword2 +
				"\nStored salt: " + hashedSalt2 + 
				"\nCompare: " + secure.validatePassword("hasLo_123", hashedPassword2, hashedSalt2, iter2)
		);
		System.out.println(
				"\nOriginal: " + original +
				"\nIterations: " + iter3 +
				"\nStored password: " + hashedPassword3 +
				"\nStored salt: " + hashedSalt3 + 
				"\nCompare: " + secure.validatePassword("hasLo_123", hashedPassword3, hashedSalt1, iter1)
		);*/
		
		
		String original = "hasLo_123";
		HashObject hashObj = new HashObject(original);
		HashCommand cmd = new PasswordHashing(hashObj);
		
		cmd.generateHash();
		
		
		System.out.println(
				"Original: " + hashObj.getOriginalValue() +
				"\nIterations: " + hashObj.getStoredIterations() +
				"\nStored password: " + hashObj.getStoredValue() +
				"\nStored salt: " + hashObj.getStoredSalt() +
				"\nCompare: " + cmd.validate()
		);
		
		
		
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static int pbkdf2Iterations = 10000;
	private static String pbkdf2Algorithm  = "PBKDF2WithHmacSHA512";
	private static String secureRandomAlgorithm  = "SHA1PRNG";
	private static int hashSize = 512;
	
	
	
	
	
	
	
	
	
	private static String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		//int iterations = 5000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, pbkdf2Iterations, hashSize);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(pbkdf2Algorithm);
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return pbkdf2Iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[hashSize];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}


	private static boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		//int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, pbkdf2Iterations, hashSize);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(pbkdf2Algorithm);
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	
	
	
	
	
	
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
