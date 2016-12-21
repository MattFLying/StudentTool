package model.human.details;

import java.text.ParseException;
import java.util.Date;

public class PersonalDetails extends Details {
	private String firstName, secondName, lastName, phoneNumber, pesel;
	private Date birthDate;
	private Address address;
	
	
	public PersonalDetails() {
		this.firstName = null;
		this.secondName = null;
		this.lastName = null;
		this.birthDate = null;
		this.pesel = null;
		this.address = null;
		this.phoneNumber = null;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return DATE_PATTERN.format(this.birthDate).toString();
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setBirthDate(String birthDate) {
		try {
			this.birthDate = DATE_PATTERN.parse(birthDate);
		} catch (ParseException e) {
			System.err.println("Blad parsowania, podaj date w formacie: dd-MM-yyyy");
		}
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}