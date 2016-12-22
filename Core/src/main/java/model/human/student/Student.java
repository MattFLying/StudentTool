package model.human.student;

import model.human.Person;
import model.human.PersonalDetails;

public class Student extends Person<StudentDetails> implements IStudent {
	private StudentDetails studentDetails;
	
	
	public Student() {
		super();
		this.studentDetails = new StudentDetails();
	}
	public Student(StudentDetails details) {
		super();
		this.studentDetails = details;
	}
	public Student(PersonalDetails details, StudentDetails studentDetails) {
		super(details);
		this.studentDetails = studentDetails;
	}
	
	
	public StudentDetails getSpecificDetails() {
		return studentDetails;
	}
	public void setSpecificDetails(StudentDetails details) {
		this.studentDetails = details;
	}
	

	

	

}