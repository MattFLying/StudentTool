package core.study.grade;

import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.details.GradeType;
import core.study.details.StudyDetails;

public class GradeDetails extends StudyDetails {
	private Float value;
	private String description;
	private GradeType gradeType;
	private Course course;
	private Student student;
	private Teacher teacher;
	
	
	public GradeDetails() {
		super();
	}
	

	@Override
	protected void initialize() {
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