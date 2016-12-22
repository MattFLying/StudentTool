package model.human;

import model.data.details.Details;

public abstract class Person<T extends Details> implements IPerson<T> {
	private PersonalDetails details;
	
	
	public Person() {
		this.details = new PersonalDetails();
	}
	public Person(PersonalDetails details) {
		this.details = details;
	}
	
	
	public PersonalDetails getPersonalDetails() {
		return details;
	}
	public void setPersonalDetails(PersonalDetails details) {
		this.details = details;
	}
}