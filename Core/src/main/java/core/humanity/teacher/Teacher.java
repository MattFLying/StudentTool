package core.humanity.teacher;

import java.util.ArrayList;
import java.util.List;

import core.model.base.humanity.Person;
import core.study.course.Course;

public class Teacher extends Person<Teacher, TeacherDetails> implements ITeacher {
	private List<Course> courses;
	
	
	public Teacher() {
		super(new TeacherDetails());
		this.courses = new ArrayList<Course>();
	}
	public Teacher(TeacherDetails details) {
		super(details);
		this.courses = new ArrayList<Course>();
	}
	
	
	public Teacher getModel() {
		return this;
	}
	public void setModel(Teacher model) {
		setDetails(model.getDetails());
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void addCourse(Course course) {
		this.courses.add(course);
	}
}