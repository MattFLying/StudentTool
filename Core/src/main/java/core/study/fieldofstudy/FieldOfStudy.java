package core.study.fieldofstudy;

import core.model.base.study.Study;

/***
 * Class represents field of study.
 * 
 * @author Mateusz Mucha
 *
 */
public class FieldOfStudy extends Study<FieldOfStudy, FieldOfStudyDetails> implements IFieldOfStudy {

	/***
	 * Default constructor sets basic fields.
	 */
	public FieldOfStudy() {
		super(new FieldOfStudyDetails());
	}

	/***
	 * Default constructor sets basic field field of study details.
	 */
	public FieldOfStudy(FieldOfStudyDetails details) {
		super(details);
	}

	public FieldOfStudy getModel() {
		return this;
	}

	public void setModel(FieldOfStudy model) {
		setDetails(model.getDetails());
	}
}