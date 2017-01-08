package core.study.course;

import core.study.details.CourseForm;
import core.study.details.StudyDetails;
import core.study.fieldofstudy.FieldOfStudy;

public class CourseDetails extends StudyDetails {
	private String courseName;
	private Integer ects, term;
	private CourseForm courseForm;
	private FieldOfStudy fieldOfStudy;
	
	
	public CourseDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
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
	public void setCourseForm(String courseForm) {
		if(courseForm.equals(CourseForm.EXERCISES.toString()))
			this.courseForm = CourseForm.EXERCISES;
		if(courseForm.equals(CourseForm.LABORATORY.toString()))
			this.courseForm = CourseForm.LABORATORY;
		if(courseForm.equals(CourseForm.LECTURE.toString()))
			this.courseForm = CourseForm.LECTURE;
		if(courseForm.equals(CourseForm.PROJECT.toString()))
			this.courseForm = CourseForm.PROJECT;
	}
}