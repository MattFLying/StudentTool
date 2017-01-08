package core.humanity.details;

import java.util.Date;

import core.model.base.Details;

public class PersonalDetails extends Details {
	private String firstName, secondName, lastName, phoneNumber;
	private Long pesel;
	private Date birthDate;
	private Address address;
	private ResearchTitle title;
	
	
	public PersonalDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
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
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Long getPesel() {
		return pesel;
	}
	public void setPesel(Long pesel) {
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
	public ResearchTitle getTitle() {
		return title;
	}
	public void setTitle(ResearchTitle title) {
		this.title = title;
	}
}