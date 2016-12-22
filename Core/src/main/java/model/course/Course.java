package model.course;

public class Course implements ICourse {
	private CourseDetails details;
	
	
	public Course() {
		this.details = new CourseDetails();
	}
	public Course(CourseDetails details) {
		this.details = details;
	}
	

	public CourseDetails getCourse() {
		return details;
	}
	public void setCourse(CourseDetails details) {
		this.details = details;
	}
}