package model.security.base;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface HashCommand {
	boolean validate() throws NoSuchAlgorithmException, InvalidKeySpecException;
	void generateHash() throws NoSuchAlgorithmException, InvalidKeySpecException;
}