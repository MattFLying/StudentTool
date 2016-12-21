package model.human;

import model.human.details.PersonalDetails;

public interface IPerson<T> {
	T getSpecificDetails();
	void setSpecificDetails(T details);
	PersonalDetails getPersonalDetails();
	void setPersonalDetails(PersonalDetails details);
}