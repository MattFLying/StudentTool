package core.study.grade;

import core.model.base.study.Study;

/***
 * Class represents grade.
 * 
 * @author Mateusz Mucha
 *
 */
public class Grade extends Study<Grade, GradeDetails> implements IGrade {

	/***
	 * Default constructor sets basic fields.
	 */
	public Grade() {
		super(new GradeDetails());
	}

	/***
	 * Default constructor sets basic field grade details.
	 * 
	 * @param details
	 *            - grade details
	 */
	public Grade(GradeDetails details) {
		super(details);
	}

	public Grade getModel() {
		return this;
	}

	public void setModel(Grade model) {
		setDetails(model.getDetails());
	}
}