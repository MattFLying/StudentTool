package core.study.course;

import core.study.details.CourseForm;
import core.study.details.StudyDetails;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.IFieldOfStudy;

/***
 * Class represents course details.
 * 
 * @author Mateusz Mucha
 *
 */
public class CourseDetails extends StudyDetails {
	private String courseName;
	private Integer term;
	private CourseForm courseForm;
	private CourseForm[] courseForms;
	private FieldOfStudy fieldOfStudy;

	/***
	 * Default constructor sets basic fields.
	 */
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

	/***
	 * Method to gets course name.
	 * 
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/***
	 * Method to sets course name.
	 * 
	 * @param name
	 *            - course name
	 */
	public void setCourseName(String name) {
		this.courseName = name;
	}

	/***
	 * Method to gets course term.
	 * 
	 * @return course term
	 */
	public Integer getTerm() {
		return term;
	}

	/***
	 * Method to sets course term.
	 * 
	 * @param term
	 *            - course term
	 */
	public void setTerm(Integer term) {
		this.term = term;
	}

	/***
	 * Method to gets field of study for course.
	 * 
	 * @return field of study for course
	 */
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}

	/***
	 * Method to sets field of study for course.
	 * 
	 * @param fieldOfStudy
	 *            - field of study interface for course
	 */
	public void setFieldOfStudy(IFieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy.getModel();
	}

	/***
	 * Method to sets field of study for course.
	 * 
	 * @param fieldOfStudy
	 *            - field of study for course
	 */
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	/***
	 * Method to gets course form.
	 * 
	 * @return course form
	 */
	public CourseForm getCourseForm() {
		return courseForm;
	}

	/***
	 * Method to sets course form.
	 * 
	 * @param courseForm
	 *            - course form
	 */
	public void setCourseForm(CourseForm courseForm) {
		this.courseForm = courseForm;
	}

	/***
	 * Method to sets course form.
	 * 
	 * @param courseForm
	 *            - course form as string value
	 */
	public void setCourseForm(String courseForm) {
		if (courseForm.equals(CourseForm.EXERCISES.getName()))
			this.courseForm = CourseForm.EXERCISES;
		if (courseForm.equals(CourseForm.LABORATORY.getName()))
			this.courseForm = CourseForm.LABORATORY;
		if (courseForm.equals(CourseForm.LECTURE.getName()))
			this.courseForm = CourseForm.LECTURE;
		if (courseForm.equals(CourseForm.PROJECT.getName()))
			this.courseForm = CourseForm.PROJECT;
	}

	/***
	 * Method to gets all course forms.
	 * 
	 * @return all course forms
	 */
	public CourseForm[] getCourseForms() {
		return courseForms;
	}

	/***
	 * Method to sets all course forms.
	 * 
	 * @param courseForms
	 *            - all course forms
	 */
	public void setCourseForms(CourseForm[] courseForms) {
		this.courseForms = courseForms;
	}
}