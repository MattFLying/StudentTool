package core.study.fieldofstudy;

import core.model.base.study.Study;

public class FieldOfStudy extends Study<FieldOfStudy, FieldOfStudyDetails> implements IFieldOfStudy {
	
	
	public FieldOfStudy() {
		super(new FieldOfStudyDetails());
	}
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