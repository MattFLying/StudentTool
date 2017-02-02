package core.humanity.teacher;

import core.model.base.humanity.Person;

/***
 * Class represents teacher.
 * 
 * @author Mateusz Mucha
 *
 */
public class Teacher extends Person<Teacher, TeacherDetails> implements ITeacher {

	/***
	 * Default constructor sets basic fields.
	 */
	public Teacher() {
		super(new TeacherDetails());
	}

	/***
	 * Default constructor sets basic field of teacher details.
	 * 
	 * @param details
	 *            - teacher details type
	 */
	public Teacher(TeacherDetails details) {
		super(details);
	}

	public Teacher getModel() {
		return this;
	}

	public void setModel(Teacher model) {
		setDetails(model.getDetails());
	}
}