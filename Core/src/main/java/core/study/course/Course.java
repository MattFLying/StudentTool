package core.study.course;

import core.model.base.study.Study;

public class Course extends Study<Course, CourseDetails> implements ICourse {
	
	
	public Course() {
		super(new CourseDetails());
	}
	public Course(CourseDetails details) {
		super(details);
	}
	

	public Course getModel() {
		return this;
	}
	public void setModel(Course model) {
		setDetails(model.getDetails());
	}
}