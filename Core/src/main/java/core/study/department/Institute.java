package core.study.department;

import core.model.base.study.Study;

/***
 * Class represents institute.
 * 
 * @author Mateusz Mucha
 *
 */
public class Institute extends Study<Institute, InstituteDetails> implements IInstitute {

	/***
	 * Default constructor sets basic fields.
	 */
	public Institute() {
		super(new InstituteDetails());
	}

	/***
	 * Default constructor sets basic field institute details.
	 */
	public Institute(InstituteDetails details) {
		super(details);
	}

	public Institute getModel() {
		return this;
	}

	public void setModel(Institute model) {
		setDetails(model.getDetails());
	}
}