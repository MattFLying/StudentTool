package core.humanity.student;

import core.model.base.humanity.Person;

/***
 * Class represents student.
 * 
 * @author Mateusz Mucha
 *
 */
public class Student extends Person<Student, StudentDetails> implements IStudent {
	/***
	 * Default constructor sets basic fields.
	 */
	public Student() {
		super(new StudentDetails());
	}

	/***
	 * Default constructor sets basic field of details.
	 * 
	 * @param details
	 *            - details of student type
	 */
	public Student(StudentDetails details) {
		super(details);
	}

	public Student getModel() {
		return this;
	}

	public void setModel(Student model) {
		setDetails(model.getDetails());
	}
}