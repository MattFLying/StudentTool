package model.human.teacher;

import model.human.Person;
import model.human.details.PersonalDetails;
import model.human.details.TeacherDetails;

public class Teacher extends Person<TeacherDetails> implements ITeacher {
	private TeacherDetails teacherDetails;
	
	
	public Teacher() {
		super();
		this.teacherDetails = new TeacherDetails();
	}
	public Teacher(TeacherDetails details) {
		super();
		this.teacherDetails = details;
	}
	public Teacher(PersonalDetails details, TeacherDetails teacherDetails) {
		super(details);
		this.teacherDetails = teacherDetails;
	}
	
	
	public TeacherDetails getSpecificDetails() {
		return teacherDetails;
	}
	public void setSpecificDetails(TeacherDetails details) {
		this.teacherDetails = details;
	}
	
}