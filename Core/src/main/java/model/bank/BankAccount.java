package model.bank;

public class BankAccount {
	private String bankNumber;

	
	public BankAccount() {
		this.bankNumber = null;
	}
	
	
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
}