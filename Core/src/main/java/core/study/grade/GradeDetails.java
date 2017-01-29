package core.study.grade;

import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.details.GradeType;
import core.study.details.StudyDetails;

/***
 * Class represents grade details.
 * 
 * @author Mateusz Mucha
 *
 */
public class GradeDetails extends StudyDetails {
	private Float value;
	private String description;
	private GradeType gradeType;
	private Course course;
	private Student student;
	private Teacher teacher;

	/***
	 * Default constructor sets basic fields.
	 */
	public GradeDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.value = null;
		this.gradeType = null;
		this.description = null;
		this.course = new Course();
		this.student = new Student();
		this.teacher = new Teacher();
	}

	/***
	 * Method to gets grade value.
	 * 
	 * @return grade value
	 */
	public Float getValue() {
		return value;
	}

	/***
	 * Method to sets grade value.
	 * 
	 * @param value
	 *            - grade value
	 */
	public void setValue(Float value) {
		this.value = value;
	}

	/***
	 * Method to gets grade type.
	 * 
	 * @return grade type
	 */
	public GradeType getGradeType() {
		return gradeType;
	}

	/***
	 * Method to sets grade type.
	 * 
	 * @param gradeType
	 *            - grade type
	 */
	public void setGradeType(GradeType gradeType) {
		this.gradeType = gradeType;
	}

	/***
	 * Method to sets grade type.
	 * 
	 * @param gradeType
	 *            - grade type as string value
	 */
	public void setGradeType(String gradeType) {
		if (gradeType.equals(GradeType.NORMAL.getName()))
			this.gradeType = GradeType.NORMAL;
		if (gradeType.equals(GradeType.SEMESTRAL.getName()))
			this.gradeType = GradeType.SEMESTRAL;
		if (gradeType.equals(GradeType.EXAM.getName()))
			this.gradeType = GradeType.EXAM;
	}

	/***
	 * Method to gets grade description.
	 * 
	 * @return grade description
	 */
	public String getDescription() {
		return description;
	}

	/***
	 * Method to sets grade description.
	 * 
	 * @param description
	 *            - grade description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/***
	 * Method to gets course of grade.
	 * 
	 * @return course of grade
	 */
	public Course getCourse() {
		return course;
	}

	/***
	 * Method to sets course of grade.
	 * 
	 * @param course
	 *            - course of grade
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/***
	 * Method to gets student of grade.
	 * 
	 * @return student of grade
	 */
	public Student getStudent() {
		return student;
	}

	/***
	 * Method to sets student of grade.
	 * 
	 * @param student
	 *            - student of grade
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/***
	 * Method to gets teacher of grade.
	 * 
	 * @return teacher of grade
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/***
	 * Method to sets teacher of grade.
	 * 
	 * @param teacher
	 *            - teacher of grade
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}