package core.study.department;

import core.model.base.study.Study;

public class Institute extends Study<Institute, InstituteDetails> implements IInstitute {
	
	
	public Institute() {
		super(new InstituteDetails());
	}
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