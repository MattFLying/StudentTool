package core.study.course;

import core.model.base.study.Study;

/***
 * Class represents course.
 * 
 * @author Mateusz Mucha
 *
 */
public class Course extends Study<Course, CourseDetails> implements ICourse {

	/***
	 * Default constructor sets basic fields.
	 */
	public Course() {
		super(new CourseDetails());
	}

	/***
	 * Default constructor sets basic field course details.
	 * 
	 * @param details
	 *            - course details
	 */
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