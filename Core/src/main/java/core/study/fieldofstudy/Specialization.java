package core.study.fieldofstudy;

import core.model.base.study.Study;

public class Specialization extends Study<Specialization, SpecializationDetails> implements ISpecialization {
	
	
	public Specialization() {
		super(new SpecializationDetails());
	}
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