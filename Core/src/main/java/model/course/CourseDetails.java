package model.course;

import model.data.details.Details;
import model.data.details.enums.CourseForm;
import model.fieldofstudy.FieldOfStudy;

public class CourseDetails extends Details {
	private String courseName;
	private Integer ects, term;
	private CourseForm courseForm;
	private FieldOfStudy fieldOfStudy;
	
	
	public CourseDetails() {
		this.courseName = null;
		this.ects = null;
		this.term = null;
		this.fieldOfStudy = null;
		this.courseForm = null;
	}
	

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public Integer getEcts() {
		return ects;
	}
	public void setEcts(Integer ects) {
		this.ects = ects;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public CourseForm getCourseForm() {
		return courseForm;
	}
	public void setCourseForm(CourseForm courseForm) {
		this.courseForm = courseForm;
	}
}