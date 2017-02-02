package core.study.fieldofstudy;

import core.model.base.study.Study;

/***
 * Class represents specialization.
 * 
 * @author Mateusz Mucha
 *
 */
public class Specialization extends Study<Specialization, SpecializationDetails> implements ISpecialization {

	/***
	 * Default constructor sets basic fields.
	 */
	public Specialization() {
		super(new SpecializationDetails());
	}

	/***
	 * Default constructor sets basic field specialization details.
	 */
	public Specialization(SpecializationDetails details) {
		super(details);
	}

	public Specialization getModel() {
		return this;
	}

	public void setModel(Specialization model) {
		setDetails(model.getDetails());
	}
}