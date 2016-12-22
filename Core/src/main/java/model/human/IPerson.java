package model.human;

public interface IPerson<T> {
	T getSpecificDetails();
	void setSpecificDetails(T details);
	PersonalDetails getPersonalDetails();
	void setPersonalDetails(PersonalDetails details);
}