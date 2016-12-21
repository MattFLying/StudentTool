package model.secure.hash;

public class HashObject {
	private String originalValue, storedValue, storedSalt;
	private int storedIterations;
	
	
	public HashObject(String originalValue) {
		this.originalValue = originalValue;
	}

	
	public HashObject configure(String storedValue, String storedSalt, int storedIterations) {
		setStoredIterations(storedIterations);
		setStoredSalt(storedSalt);
		setStoredValue(storedValue);
		
		return this;
	}
	public String getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}
	public String getStoredValue() {
		return storedValue;
	}
	public void setStoredValue(String storedValue) {
		this.storedValue = storedValue;
	}
	public String getStoredSalt() {
		return storedSalt;
	}
	public void setStoredSalt(String storedSalt) {
		this.storedSalt = storedSalt;
	}
	public int getStoredIterations() {
		return storedIterations;
	}
	public void setStoredIterations(int storedIterations) {
		this.storedIterations = storedIterations;
	}
}