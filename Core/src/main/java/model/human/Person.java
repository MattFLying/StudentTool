package model.human;

import model.human.details.Details;
import model.human.details.PersonalDetails;

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