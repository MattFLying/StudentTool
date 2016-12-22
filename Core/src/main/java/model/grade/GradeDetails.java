package model.grade;

import model.course.Course;
import model.data.details.Details;
import model.data.details.enums.GradeType;
import model.human.student.Student;
import model.human.teacher.Teacher;

public class GradeDetails extends Details {
	private Float value;
	private String description;
	private GradeType gradeType;
	private Course course;
	private Student student;
	private Teacher teacher;
	
	
	public GradeDetails() {
		this.value = null;
		this.gradeType = null;
		this.description = null;
		this.course = null;
		this.student = null;
		this.teacher = null;
	}
	
	
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public GradeType getGradeType() {
		return gradeType;
	}
	public void setGradeType(GradeType gradeType) {
		this.gradeType = gradeType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}