package core.study.course;

import core.study.details.CourseForm;
import core.study.details.StudyDetails;
import core.study.fieldofstudy.FieldOfStudy;

public class CourseDetails extends StudyDetails {
	private String courseName;
	private Integer term;
	private CourseForm courseForm;
	private CourseForm[] courseForms;
	private FieldOfStudy fieldOfStudy;
	
	
	public CourseDetails() {
		super();
	}
	
	
	@Override
	protected void initialize() {
		this.courseName = null;
		this.term = null;
		this.fieldOfStudy = new FieldOfStudy();
		this.courseForm = null;
		this.courseForms = new CourseForm[0];
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
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
		if(courseForm.equals(CourseForm.EXERCISES.getName()))
			this.courseForm = CourseForm.EXERCISES;
		if(courseForm.equals(CourseForm.LABORATORY.getName()))
			this.courseForm = CourseForm.LABORATORY;
		if(courseForm.equals(CourseForm.LECTURE.getName()))
			this.courseForm = CourseForm.LECTURE;
		if(courseForm.equals(CourseForm.PROJECT.getName()))
			this.courseForm = CourseForm.PROJECT;
	}
	public CourseForm[] getCourseForms() {
		return courseForms;
	}
	public void setCourseForms(CourseForm[] courseForms) {
		this.courseForms = courseForms;
	}
}